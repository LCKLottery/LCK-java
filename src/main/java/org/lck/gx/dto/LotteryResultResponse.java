package org.lck.gx.dto;

import lombok.Data;

import java.util.List;

@Data
public class LotteryResultResponse {
    private long round;
    private String prizePool;
    private List<String> winners;

    public LotteryResultResponse(long round, String prizePool, List<String> winners) {
        this.round = round;
        this.prizePool = prizePool;
        this.winners = winners;
    }
}
