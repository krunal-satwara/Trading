package com.trade.sequenceservice;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TradeSequenceServiceApplication {

    private static final Logger LOGGER = LoggerFactory.getLogger(TradeSequenceServiceApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(TradeSequenceServiceApplication.class, args);
        LOGGER.info("Sequence Service Started");
    }

}
