package org.lck.gx.service;

import org.lck.gx.dto.ProofResponse;
import org.lck.gx.entity.AirdropBatch;

import java.math.BigInteger;
import java.util.Map;

public interface AirdropService {


    AirdropBatch createBatch(String name, Map<String, BigInteger> addrAmountMap);

    ProofResponse getProof(Long batchId, String address);

}
