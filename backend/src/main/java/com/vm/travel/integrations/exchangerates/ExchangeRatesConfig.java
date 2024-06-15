package com.vm.travel.integrations.exchangerates;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ExchangeRatesConfig {
    @Value("${exchangerates.url}")
    public String baseUrl;
    @Value("${exchangerates.api.key}")
    public String apiKey;
}
