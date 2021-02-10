package com.trade.transferhistoryservice;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@EnableDiscoveryClient
public class TradeTransferhistoryServiceApplication {

    private static final Logger LOGGER = LoggerFactory.getLogger(TradeTransferhistoryServiceApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(TradeTransferhistoryServiceApplication.class, args);
        LOGGER.info("Transfer Service Started");
    }

    @Bean
    RestTemplate getRestTemplate(){
        return new RestTemplate();
    }

}
