package org.lck.gx.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.lck.gx.dto.ProofResponse;
import org.lck.gx.entity.AirdropAllocation;
import org.lck.gx.entity.AirdropBatch;
import org.lck.gx.repo.AirdropAllocationRepository;
import org.lck.gx.repo.AirdropBatchRepository;
import org.lck.gx.service.AirdropService;
import org.lck.gx.util.MerkleTree;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.*;

@Service
public class AirdropServiceImpl implements AirdropService {
    private final AirdropBatchRepository batchRepo;
    private final AirdropAllocationRepository allocRepo;
    private final ObjectMapper om = new ObjectMapper();

    public AirdropServiceImpl(AirdropBatchRepository batchRepo, AirdropAllocationRepository allocRepo) {
        this.batchRepo = batchRepo;
        this.allocRepo = allocRepo;
    }

    @Transactional
    @Override
    public AirdropBatch createBatch(String name, Map<String, BigInteger> addrAmountMap) {
        // 1) 用确定性顺序生成 Merkle
        MerkleTree tree = new MerkleTree();
        tree.init(addrAmountMap);
        String root = tree.getRoot();

        // 2) 入库 batch
        AirdropBatch batch = AirdropBatch.builder()
                .name(name)
                .merkleRoot(root)
                .build();
        batch = batchRepo.save(batch);

        // 3) 为每个地址生成 proof 并落库
        int i = 0;
        // 使用与 MerkleTree 相同的顺序：根据 addressIndexMap（通过 getProofByAddress 推断索引）
        for (Map.Entry<String, BigInteger> e : addrAmountMap.entrySet()) {
            String addr = e.getKey();
            BigInteger amount = e.getValue();

            List<String> proof = tree.getProofByAddress(addr);
            // 反算索引（可在 MerkleTree 暴露 getIndex(address) 提高效率；这里演示获取）
            int index = resolveIndex(tree, addr, proof);

            String leaf = computeLeafHex(addr, amount); // 与 MerkleTree 一致
            String proofJson;
            try {
                proofJson = om.writeValueAsString(proof);
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }

            AirdropAllocation alloc = AirdropAllocation.builder()
                    .batchId(batch.getId())
                    .addr(addr)
                    .amountWei(new BigDecimal(amount)) // 存 DECIMAL(78,0)
                    .leaf(leaf)
                    .addrIndex(index)
                    .proofJson(proofJson)
                    .build();

            allocRepo.save(alloc);
            i++;
        }
        return batch;
    }

    @Override
    public ProofResponse getProof(Long batchId, String address) {
        AirdropBatch batch = batchRepo.findById(batchId)
                .orElseThrow(() -> new NoSuchElementException("batch not found"));
        AirdropAllocation alloc = allocRepo.findByBatchIdAndAddrIgnoreCase(batchId, address)
                .orElseThrow(() -> new NoSuchElementException("address not in batch"));

        List<String> proof;
        try {
            proof = Arrays.asList(om.readValue(alloc.getProofJson(), String[].class));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return new ProofResponse(
                alloc.getAddr(),
                alloc.getAmountWei().toPlainString(),
                batch.getMerkleRoot(),
                proof,
                alloc.getAddrIndex()
        );
    }

    /* -------------------- 工具方法 -------------------- */

    private static String computeLeafHex(String address, BigInteger amount) {
        // 与 MerkleTree.makeLeaf 一致（为了展示清晰，直接 new 一个树用其静态逻辑也可以）
        // 这里简单起见复用 MerkleTree.verifyProof 的第一步：
        List<String> empty = Collections.emptyList();
        boolean ok = MerkleTree.verifyProof(address, amount, empty, "0x" + "0".repeat(64)); // 计算 leaf
        // 上面 verifyProof 不会改变 leaf；我们直接产生 leaf：
        try {
            String leaf = MerkleTree.makeLeafHex(address, amount);
            // 反射拿私有方法不优雅；推荐把 MerkleTree.makeLeafHex(address, amount) 暴露出来
            // 这里为了简明，复制逻辑到 MerkleTree 并提供静态方法：
            return leaf;
        } catch (UnsupportedOperationException e) {
            // 占位：你应在 MerkleTree 里新增：
            throw e;
        }
    }

    // 仅演示：通过 proof 回推索引（更好的方式是 MerkleTree 暴露 address->index）
    private static int resolveIndex(MerkleTree tree, String addr, List<String> proof) {
        return tree.getIndex(addr);
    }
}
