package com.trade.zuulservice.configuration;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;
import springfox.documentation.swagger.web.SwaggerResource;
import springfox.documentation.swagger.web.SwaggerResourcesProvider;

import java.util.ArrayList;
import java.util.List;

@Component
@Primary
@EnableAutoConfiguration
public class SwaggerCentralConfiguration implements SwaggerResourcesProvider {

    @Override
    public List get() {
        List resources = new ArrayList<>();
        resources.add(swaggerResource("trade-zuul-service", "/api/zuul-service/v2/api-docs", "2.0"));
        resources.add(swaggerResource("trade-account-service", "/api/account-service/v2/api-docs", "2.0"));
        resources.add(swaggerResource("trade-customer-service", "/api/customer-service/v2/api-docs", "2.0"));
        resources.add(swaggerResource("trade-deposit-service", "/api/deposit-service/v2/api-docs", "2.0"));
        resources.add(swaggerResource("trade-sequence-service", "/api/sequence-service/v2/api-docs", "2.0"));
        resources.add(swaggerResource("trade-transferhistory-service", "/api/transferhistory-service/v2/api-docs", "2.0"));
        resources.add(swaggerResource("trade-withdrawal-service", "/api/withdrawal-service/v2/api-docs", "2.0"));
        /*resources.add(swaggerResource("trade-auth-service", "/api/T7/v2/api-docs", "2.0"));*/
        return resources;
    }

    private SwaggerResource swaggerResource(String name, String location, String version) {
        SwaggerResource swaggerResource = new SwaggerResource();
        swaggerResource.setName(name);
        swaggerResource.setLocation(location);
        swaggerResource.setSwaggerVersion(version);
        return swaggerResource;
    }

}
