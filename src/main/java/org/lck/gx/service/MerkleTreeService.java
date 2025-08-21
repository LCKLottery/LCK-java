package org.lck.gx.service;

import java.math.BigInteger;
import java.util.List;

public interface MerkleTreeService {
    /**
     * 将地址+金额 转换为 leaf
     * @param address
     * @param amount
     * @return
     */
    byte[] createLeaf(String address, BigInteger amount);

    /**
     * 构建 Merkle Tree 并返回 Root
     * @param leaves
     * @return
     */
    byte[] buildMerkleRoot(List<byte[]> leaves);

}
