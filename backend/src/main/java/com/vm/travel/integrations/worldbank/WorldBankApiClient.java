package com.vm.travel.integrations.worldbank;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.vm.travel.integrations.worldbank.deserializers.GDPResponseDeserializer;
import com.vm.travel.integrations.worldbank.deserializers.PopulationResponseDeserializer;
import com.vm.travel.integrations.worldbank.dto.GDPResponse;
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
public class WorldBankApiClient {
    private final Logger logger = LoggerFactory.getLogger(WorldBankApiClient.class);
    private final WebClient webClient;
    private final WorldBankApiConfig config;

    public CompletableFuture<PopulationResponse> getCountryPopulationDataByCountryCode(String countryCode) {
        String url = String.format("%s/country/%s/indicator/SP.POP.TOTL?format=json", config.baseUrl, countryCode);

        ObjectMapper mapper = new ObjectMapper();
        SimpleModule module = new SimpleModule();
        module.addDeserializer(PopulationResponse.class, new PopulationResponseDeserializer());
        mapper.registerModule(module);

        return webClient.get()
                .uri(url)
                .retrieve()
                .bodyToMono(String.class)
                .map(body -> {
                    try {
                        return mapper.readValue(body, PopulationResponse.class);
                    } catch (IOException e) {
                        throw new RuntimeException("Failed to deserialize population response in getCountryPopulationDataByCountryCode method of WorldBankApiClient", e);
                    }
                })
                .toFuture()
                .exceptionally(ex -> {
                    logger.error("An exception occurred while trying to fetch population data for countryCode: {}", countryCode, ex);
                    return null;
                });
    }


    public CompletableFuture<GDPResponse> getCountryGdpDataByCountryCode(String countryCode) {
        String url = String.format("%s/country/%s/indicator/NY.GDP.MKTP.CD?format=json", config.baseUrl, countryCode);

        ObjectMapper mapper = new ObjectMapper();
        SimpleModule module = new SimpleModule();
        module.addDeserializer(GDPResponse.class, new GDPResponseDeserializer());
        mapper.registerModule(module);

        return webClient.get()
                .uri(url)
                .retrieve()
                .bodyToMono(String.class)
                .map(body -> {
                    try {
                        return mapper.readValue(body, GDPResponse.class);
                    } catch (IOException e) {
                        throw new RuntimeException("Failed to deserialize GDP response in getCountryGdpDataByCountryCode method of WorldBankApiClient", e);
                    }
                })
                .toFuture()
                .exceptionally(ex -> {
                    logger.error("An exception occurred while trying to fetch GDP data for countryCode: {}", countryCode, ex);
                    return null;
                });
    }


}
