package com.trade.accountservice;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@EnableDiscoveryClient
public class AccountTradeServiceApplication {
    private static final Logger LOGGER = LoggerFactory.getLogger(AccountTradeServiceApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(AccountTradeServiceApplication.class, args);
        LOGGER.info("Account Service Started");
    }

    @Bean
    RestTemplate getRestTemplate(){
        return new RestTemplate();
    }

}
