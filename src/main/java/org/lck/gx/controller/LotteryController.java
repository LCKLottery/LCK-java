package org.lck.gx.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/select")
public class LotteryController {

/*    @GetMapping("/winners/{round}")
    public List<Long> getWinners(@PathVariable Long round) {
        return winnerService.getWinners(round);
    }

    @GetMapping("/round/current")
    public Long getCurrentRound() {
        return lotteryService.getCurrentRound();
    }*/


}
