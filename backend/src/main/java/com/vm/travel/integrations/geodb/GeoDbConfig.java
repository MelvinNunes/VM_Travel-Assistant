package com.vm.travel.integrations.geodb;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GeoDbConfig {
    @Value("${geodb.url}")
    public String baseUrl;
}
