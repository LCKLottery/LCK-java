package org.lck.gx.util;

import org.bouncycastle.jcajce.provider.digest.Keccak;
import org.bouncycastle.util.encoders.Hex;

import java.math.BigInteger;
import java.util.*;

/**
 * 与 Solidity keccak256(abi.encodePacked(address, uint256)) 完全对齐，
 * 并按 OpenZeppelin MerkleProof 的“sorted pairs”规则构建。
 */
public class MerkleTree {

    // 按确定性顺序（地址字节升序）存放的叶子（hash 后的 32 字节）
    private List<byte[]> leaves = new ArrayList<>();
    // address(lowercase) -> index（与 leaves 对应）
    private final Map<String, Integer> addressIndexMap = new HashMap<>();
    // 根（0x 前缀）
    private String root = "0x";

    public MerkleTree() {}

    /**
     * 用「地址 -> 金额(wei)」初始化，按地址字节升序构建确定性的叶子顺序
     */
    public void init(Map<String, BigInteger> addressAmountMap) {
        leaves.clear();
        addressIndexMap.clear();

        // 1) 复制 entry 并按地址 20 字节升序排序（确定性）
        List<Map.Entry<String, BigInteger>> entries = new ArrayList<>(addressAmountMap.entrySet());
        entries.sort(Comparator.comparing(e -> hexToBytes(strip0x(e.getKey().toLowerCase())), MerkleTree::compareUnsignedBytes));

        // 2) 生成叶子（keccak256(abi.encodePacked(address, amount))）
        int i = 0;
        for (Map.Entry<String, BigInteger> e : entries) {
            String addr = toChecksumOrLower(e.getKey()); // 保留原格式也行，这里统一转 lower 再解码
            BigInteger amount = e.getValue();
            byte[] leaf = makeLeaf(addr, amount);
            leaves.add(leaf);
            addressIndexMap.put(addr.toLowerCase(), i);
            i++;
        }

        // 3) 构建树（每层对 pair 按字节序排序，再 keccak256）
        root = buildRoot(leaves);
    }

    /**
     * 生成叶子：keccak256(abi.encodePacked(address, uint256))
     */
    private static byte[] makeLeaf(String address, BigInteger amount) {
        byte[] addrBytes = hexToBytes(strip0x(address));      // 20 bytes
        byte[] amtBytes  = uint256ToBytes(amount);            // 32 bytes
        byte[] packed = new byte[addrBytes.length + amtBytes.length];
        System.arraycopy(addrBytes, 0, packed, 0, addrBytes.length);
        System.arraycopy(amtBytes, 0, packed, addrBytes.length, amtBytes.length);
        return keccak256(packed);
    }

    /**
     * 构建根：每层 pair 做字节序排序后再拼接哈希（与 OZ MerkleProof.verify 对齐）
     */
    private static String buildRoot(List<byte[]> leaves) {
        if (leaves.isEmpty()) return "0x" + "0".repeat(64);

        List<byte[]> level = new ArrayList<>(leaves);
        while (level.size() > 1) {
            List<byte[]> next = new ArrayList<>((level.size() + 1) / 2);
            for (int i = 0; i < level.size(); i += 2) {
                byte[] left = level.get(i);
                byte[] right = (i + 1 < level.size()) ? level.get(i + 1) : left; // 奇数补齐
                next.add(hashPairSorted(left, right));
            }
            level = next;
        }
        return "0x" + Hex.toHexString(level.get(0));
    }

    /**
     * 生成 proof：返回兄弟节点哈希（0x 前缀的 hex 字符串）
     * 注意：树是按 sorted pair 构建的，但 proof 仅包含 sibling 值，方向由 OZ 验证时再排序处理
     */
    public List<String> getProof(int index) {
        if (index < 0 || index >= leaves.size()) throw new IllegalArgumentException("index out of range");

        List<byte[]> level = new ArrayList<>(leaves);
        int idx = index;
        List<String> proof = new ArrayList<>();

        while (level.size() > 1) {
            List<byte[]> next = new ArrayList<>((level.size() + 1) / 2);
            for (int i = 0; i < level.size(); i += 2) {
                byte[] left = level.get(i);
                byte[] right = (i + 1 < level.size()) ? level.get(i + 1) : left;

                // 记录 sibling（无需记录方向，OZ 验证时会根据大小排序）
                if (i == idx || i + 1 == idx) {
                    byte[] sibling = (i == idx) ? right : left;
                    proof.add("0x" + Hex.toHexString(sibling));
                    idx = next.size();
                }

                next.add(hashPairSorted(left, right));
            }
            level = next;
        }
        return proof;
    }

    public List<String> getProofByAddress(String address) {
        Integer idx = addressIndexMap.get(address.toLowerCase());
        if (idx == null) throw new IllegalArgumentException("Address not in Merkle tree");
        return getProof(idx);
    }

    public boolean isEligible(String address) {
        return addressIndexMap.containsKey(address.toLowerCase());
    }

    public String getRoot() {
        return root;
    }

    /** —— 辅助：在 Java 侧本地校验 proof 是否能还原为 root —— */
    public static boolean verifyProof(String address, BigInteger amount, List<String> proof, String rootHex) {
        byte[] computed = makeLeaf(address, amount);
        for (String sibHex : proof) {
            byte[] sibling = hexToBytes(strip0x(sibHex));
            computed = hashPairSorted(computed, sibling);
        }
        String got = "0x" + Hex.toHexString(computed);
        return got.equalsIgnoreCase(rootHex);
    }

    /* ----------------------- 工具方法 ----------------------- */

    private static byte[] hashPairSorted(byte[] a, byte[] b) {
        // 字节序无符号比较，较小的在前
        int cmp = compareUnsignedBytes(a, b);
        byte[] first = (cmp <= 0) ? a : b;
        byte[] second = (cmp <= 0) ? b : a;
        byte[] combined = new byte[first.length + second.length];
        System.arraycopy(first, 0, combined, 0, first.length);
        System.arraycopy(second, 0, combined, first.length, second.length);
        return keccak256(combined);
    }

    private static int compareUnsignedBytes(byte[] a, byte[] b) {
        int len = Math.min(a.length, b.length);
        for (int i = 0; i < len; i++) {
            int aa = a[i] & 0xff;
            int bb = b[i] & 0xff;
            if (aa != bb) return Integer.compare(aa, bb);
        }
        return Integer.compare(a.length, b.length);
    }

    private static byte[] uint256ToBytes(BigInteger v) {
        if (v.signum() < 0) throw new IllegalArgumentException("amount must be unsigned");
        byte[] raw = v.toByteArray(); // 可能有符号前导 0x00
        if (raw.length == 32) return raw;
        if (raw.length < 32) {
            byte[] out = new byte[32];
            System.arraycopy(raw, 0, out, 32 - raw.length, raw.length);
            return out;
        }
        // >32 字节（极大数）只保留低 32 字节（与 Solidity uint256 溢出规则一致，但正常不会发生）
        byte[] out = new byte[32];
        System.arraycopy(raw, raw.length - 32, out, 0, 32);
        return out;
    }

    private static byte[] keccak256(byte[] input) {
        Keccak.Digest256 d = new Keccak.Digest256();
        return d.digest(input);
    }

    private static String strip0x(String s) {
        return (s.startsWith("0x") || s.startsWith("0X")) ? s.substring(2) : s;
    }

    private static byte[] hexToBytes(String hexNo0x) {
        return Hex.decode(hexNo0x);
    }

    private static String toChecksumOrLower(String address) {
        // 这里直接返回原始或转小写都可以，因为我们解码为字节；关键是 Hex.decode 不区分大小写
        return address.startsWith("0x") ? address : "0x" + address;
    }
}
