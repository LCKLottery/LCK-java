// AirdropBatch.java
package org.lck.gx.entity;

import lombok.*;
import jakarta.persistence.*;
import java.time.Instant;

@Entity
@Table(name = "airdrop_batch")
@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class AirdropBatch {
  @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String name;

  @Column(name = "merkle_root", nullable = false, length = 66)
  private String merkleRoot;

  @Column(name = "created_at", nullable = false, updatable = false)
  private Instant createdAt;

  @PrePersist
  void onCreate() { if (createdAt == null) createdAt = Instant.now(); }
}
