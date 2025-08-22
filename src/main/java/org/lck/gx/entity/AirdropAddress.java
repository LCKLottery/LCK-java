package org.lck.gx.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigInteger;

@Entity
@Table(name = "airdrop_address")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AirdropAddress {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "address")
    private String address;

    @Column(name = "amount")
    private BigInteger amount;

}
