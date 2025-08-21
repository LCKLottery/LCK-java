package org.lck.gx.service.impl;

import org.bouncycastle.jcajce.provider.digest.Keccak;
import org.lck.gx.service.MerkleTreeService;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.*;
@Service
public class MerkleTreeServiceImpl implements MerkleTreeService {

    // keccak256 哈希函数
    public static byte[] keccak256(byte[] input) {
        Keccak.Digest256 digest = new Keccak.Digest256();
        return digest.digest(input);
    }


    @Override
    public byte[] createLeaf(String address, BigInteger amount) {
        String input = address.toLowerCase() + amount.toString();
        return keccak256(input.getBytes());
    }

    // 构建 Merkle Tree 并返回 Root
    @Override
    public  byte[] buildMerkleRoot(List<byte[]> leaves) {
        List<byte[]> level = new ArrayList<>(leaves);

        while (level.size() > 1) {
            List<byte[]> nextLevel = new ArrayList<>();

            for (int i = 0; i < level.size(); i += 2) {
                byte[] left = level.get(i);
                byte[] right = (i + 1 < level.size()) ? level.get(i + 1) : left;

                byte[] combined = new byte[left.length + right.length];
                System.arraycopy(left, 0, combined, 0, left.length);
                System.arraycopy(right, 0, combined, left.length, right.length);

                nextLevel.add(keccak256(combined));
            }
            level = nextLevel;
        }
        return level.get(0);
    }


/*    public static void main(String[] args) {
        Map<String, BigInteger> airdrop = new LinkedHashMap<>();
        airdrop.put("0x1111111111111111111111111111111111111111", BigInteger.valueOf(1000));
        airdrop.put("0x2222222222222222222222222222222222222222", BigInteger.valueOf(2000));
        airdrop.put("0x3333333333333333333333333333333333333333", BigInteger.valueOf(1500));

        // 生成叶子
        List<byte[]> leaves = new ArrayList<>();
        for (Map.Entry<String, BigInteger> entry : airdrop.entrySet()) {
            leaves.add(createLeaf(entry.getKey(), entry.getValue()));
        }

        // 生成 root
        byte[] root = buildMerkleRoot(leaves);

        System.out.println("Merkle Root = 0x" + new BigInteger(1, root).toString(16));
    }*/
}
