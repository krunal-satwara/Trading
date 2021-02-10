package com.trade.depositservice;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@EnableDiscoveryClient
public class TradeDepositServiceApplication {

    private static final Logger LOGGER = LoggerFactory.getLogger(TradeDepositServiceApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(TradeDepositServiceApplication.class, args);
        LOGGER.info("Deposit Service Started");
    }

    @Bean
    RestTemplate getRestTemplate(){
        return new RestTemplate();
    }
}
