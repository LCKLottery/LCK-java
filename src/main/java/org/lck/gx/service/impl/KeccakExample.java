package org.lck.gx.service.impl;

import org.bouncycastle.jcajce.provider.digest.Keccak;

public class KeccakExample {
    public static void main(String[] args) {
        Keccak.Digest256 keccak256 = new Keccak.Digest256(); // Keccak-256
        byte[] hash = keccak256.digest("hello".getBytes());

        System.out.println(bytesToHex(hash));
    }

    private static String bytesToHex(byte[] bytes) {
        StringBuilder sb = new StringBuilder();
        for (byte b : bytes) {
            sb.append(String.format("%02x", b));
        }
        return sb.toString();
    }
}
