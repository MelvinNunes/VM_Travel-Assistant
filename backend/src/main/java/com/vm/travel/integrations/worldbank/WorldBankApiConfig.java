package com.vm.travel.integrations.worldbank;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class WorldBankApiConfig {
    @Value(("${wordlbank.url}"))
    private String baseUrl;
}
