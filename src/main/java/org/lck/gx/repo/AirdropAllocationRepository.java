// AirdropAllocationRepository.java
package org.lck.gx.repo;

import org.lck.gx.entity.AirdropAllocation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface AirdropAllocationRepository extends JpaRepository<AirdropAllocation, Long> {
    Optional<AirdropAllocation> findByBatchIdAndAddrIgnoreCase(Long batchId, String addr);

    List<AirdropAllocation> findAllByBatchId(Long batchId);
}
