package com.trade.customerservice;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@EnableDiscoveryClient
public class TradeCustomerServiceApplication {

    private static final Logger LOGGER = LoggerFactory.getLogger(TradeCustomerServiceApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(TradeCustomerServiceApplication.class, args);
        LOGGER.info("Customer Service Started");
    }

    @Bean
    RestTemplate getRestTemplate(){
        return new RestTemplate();
    }

}
