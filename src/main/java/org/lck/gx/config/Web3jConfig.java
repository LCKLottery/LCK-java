package org.lck.gx.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.http.HttpService;

@Configuration
public class Web3jConfig {

    // 您可以从 application.properties 或其他配置中读取这个 URL
    private static final String RPC_URL = "http://127.0.0.1:8545";

    @Bean
    public Web3j web3j() {
        return Web3j.build(new HttpService(RPC_URL));
    }
}