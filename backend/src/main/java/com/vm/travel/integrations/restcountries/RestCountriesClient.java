package com.vm.travel.integrations.restcountries;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.vm.travel.integrations.restcountries.deserializers.RestCountriesDeserializer;
import com.vm.travel.integrations.restcountries.dto.RestCountriesResponse;
import com.vm.travel.integrations.worldbank.deserializers.PopulationResponseDeserializer;
import com.vm.travel.integrations.worldbank.dto.PopulationResponse;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import java.io.IOException;
import java.util.concurrent.CompletableFuture;

@Component
@RequiredArgsConstructor
public class RestCountriesClient {
    private final Logger logger = LoggerFactory.getLogger(RestCountriesClient.class);
    private final WebClient webClient;
    private final RestCountriesConfig config;

    public CompletableFuture<RestCountriesResponse> getAllCountries() {
        String url = String.format("%s/all?fields=name,flags,capital,region,subregion,currencies", config.baseUrl);

        ObjectMapper mapper = new ObjectMapper();
        SimpleModule module = new SimpleModule();
        module.addDeserializer(RestCountriesResponse.class, new RestCountriesDeserializer());
        mapper.registerModule(module);

        return webClient.get()
                .uri(url)
                .retrieve()
                .bodyToMono(String.class)
                .map(body -> {
                    try {
                        return mapper.readValue(body, RestCountriesResponse.class);
                    } catch (IOException e) {
                        throw new RuntimeException("Failed to deserialize countries response in getAllCountries method of RestCountriesClient", e);
                    }
                })
                .toFuture()
                .exceptionally(ex -> {
                    logger.error("Error fetching countries in getAllCountries method from RestCountriesClient");
                    logger.error("Stacktrace: ", ex);
                    return null;
                });
    }


    public CompletableFuture<RestCountriesResponse> getAllCountriesByRegion(String region) {
        String url = String.format("%s/region/%s?fields=name,flags,capital,region,subregion,currencies", config.baseUrl, region);

        ObjectMapper mapper = new ObjectMapper();
        SimpleModule module = new SimpleModule();
        module.addDeserializer(RestCountriesResponse.class, new RestCountriesDeserializer());
        mapper.registerModule(module);

        return webClient.get()
                .uri(url)
                .retrieve()
                .bodyToMono(String.class)
                .map(body -> {
                    try {
                        return mapper.readValue(body, RestCountriesResponse.class);
                    } catch (IOException e) {
                        throw new RuntimeException("Failed to deserialize countries response in getAllCountriesByRegion method of RestCountriesClient", e);
                    }
                })
                .toFuture()
                .exceptionally(ex -> {
                    logger.error("Error fetching countries in getAllCountriesByRegion method from RestCountriesClient");
                    logger.error("Stacktrace: ", ex);
                    return null;
                });
    }
}
