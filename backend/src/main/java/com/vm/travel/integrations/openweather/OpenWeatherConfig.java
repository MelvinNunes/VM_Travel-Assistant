package com.vm.travel.integrations.openweather;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenWeatherConfig {
    @Value("${openweather.url}")
    public String baseUrl;
    @Value("${openweather.key}")
    public String apiKey;
}
