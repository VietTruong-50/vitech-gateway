package com.example.apigateway;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RequestLogConfig {
    @Bean
    @ConditionalOnMissingBean(RequestFilterIn.class)
    public RequestFilterIn requestLogFilter() {
        RequestFilterIn requestLogFilter = new RequestFilterIn();
        return requestLogFilter;
    }
}
