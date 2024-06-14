package com.vm.travel.infrastructure.config;

import org.springframework.context.annotation.Bean;
import org.springframework.web.reactive.function.client.WebClient;

public class WebClientConfig {
    @Bean
    public WebClient webClient(WebClient.Builder builder) {
        return builder.build();
    }
}
