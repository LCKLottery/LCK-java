package org.lck.gx.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Table(name = "lottery_result")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LotteryResult {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 期数
     */
    @Column(name="round", nullable=false)
    private long round;

    /**
     * 池子奖金
     */
    @Column(name="prize_pool", nullable=false)
    private String prizePool;

    /**
     * 获胜者地址
     */
    @Column(name="winner", nullable=false)
    private String winner;

    /**
     * 获奖等级  1:一等奖  2:二等奖  3：三等奖  L: 幸运奖励
     */
    @Column(name="winner_rank", nullable=false)
    private String winner_rank;


}
