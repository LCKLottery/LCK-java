package org.lck.gx;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class MerkleAirdropApplication {
    public static void main(String[] args) {
        SpringApplication.run(MerkleAirdropApplication.class, args);
    }
}
