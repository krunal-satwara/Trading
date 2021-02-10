package com.trade.withdrawalservice.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;

@Configuration
@PropertySources({
        @PropertySource("classpath:withdrawal-service-url.properties")
})
public class PropertyLoader {
}
