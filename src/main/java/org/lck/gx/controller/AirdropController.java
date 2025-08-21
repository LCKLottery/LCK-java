package org.lck.gx.controller;

import org.lck.gx.util.MerkleTree;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;

import java.math.BigInteger;
import java.util.*;

@RestController
@RequestMapping("/airdrop")
public class AirdropController {

    private final MerkleTree merkleTree = new MerkleTree();


    @GetMapping("/init")
    public ResponseEntity<String> init() {
        Map<String, BigInteger > addressAmountMap = new HashMap<>();
        addressAmountMap.put("0xf39Fd6e51aad88F6F4ce6aB8827279cffFb92266", new BigInteger("1000000000000000000"));
        addressAmountMap.put("0x70997970C51812dc3A010C7d01b50e0d17dc79C8", new BigInteger("1000000000000000000"));
        addressAmountMap.put("0x3C44CdDdB6a900fa2b585dd299e03d12FA4293BC", new BigInteger("1000000000000000000"));
        merkleTree.init(addressAmountMap);
        System.out.println("Merkle Root: " + merkleTree.getRoot());
        return ResponseEntity.ok("success");
    }


    // 获取当前 Merkle Root
    @GetMapping("/root")
    public ResponseEntity<String> getMerkleRoot() {
        return ResponseEntity.ok(merkleTree.getRoot());
    }

    // 获取某个地址的 Proof
    @GetMapping("/proof")
    public ResponseEntity<Map<String, Object>> getProof(@RequestParam String address) {
        List<String> proof = merkleTree.getProofByAddress(address);
        boolean eligible = !proof.isEmpty();

        Map<String, Object> result = new HashMap<>();
        result.put("address", address);
        result.put("eligible", eligible);
        result.put("proof", proof);

        return ResponseEntity.ok(result);
    }

    // 检查某个地址是否在白名单
    @GetMapping("/check")
    public ResponseEntity<Map<String, Object>> checkEligibility(@RequestParam String address) {
        boolean eligible = merkleTree.isEligible(address);

        Map<String, Object> result = new HashMap<>();
        result.put("address", address);
        result.put("eligible", eligible);

        return ResponseEntity.ok(result);
    }
}
