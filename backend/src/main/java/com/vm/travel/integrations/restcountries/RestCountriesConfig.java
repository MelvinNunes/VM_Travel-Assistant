package com.vm.travel.integrations.restcountries;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RestCountriesConfig {
    @Value("${restcountries.url}")
    public String baseUrl;
}
