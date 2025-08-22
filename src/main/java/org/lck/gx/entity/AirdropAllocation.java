// AirdropAllocation.java
package org.lck.gx.entity;

import lombok.*;
import jakarta.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "airdrop_allocation",
        uniqueConstraints = @UniqueConstraint(name="uq_batch_addr", columnNames={"batch_id","addr"}))
@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class AirdropAllocation {
  @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name="batch_id", nullable=false)
  private Long batchId;

  @Column(name="addr", nullable=false, length=42)
  private String addr;

  // 用 BigDecimal 存 uint256（JPA 不支持 BigInteger 映射 DECIMAL），读写时在 Service 里转换
  @Column(name="amount_wei", nullable=false, precision=65, scale=0)
  private BigDecimal amountWei;

  @Column(name="leaf", nullable=false, length=66)
  private String leaf;

  @Column(name="addr_index", nullable=false)
  private Integer addrIndex;

  // 简化：用 TEXT 存 JSON（或 columnDefinition="json"）
  @Lob
  @Column(name="proof_json", nullable=false, columnDefinition = "json")
  private String proofJson;
}
