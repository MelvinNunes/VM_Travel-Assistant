package com.vm.travel.integrations.geodb;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.vm.travel.integrations.geodb.deserializers.GeoDbDeserializer;
import com.vm.travel.integrations.geodb.dto.GeoDbRes;
import com.vm.travel.integrations.worldbank.deserializers.PopulationResponseDeserializer;
import com.vm.travel.integrations.worldbank.dto.PopulationResponse;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import java.io.IOException;
import java.util.concurrent.CompletableFuture;

@Component
@RequiredArgsConstructor
public class GeoDbClient {
    private final WebClient webClient;
    private final GeoDbConfig geoDbConfig;
    private final Logger logger = LoggerFactory.getLogger(GeoDbClient.class);

    public CompletableFuture<GeoDbRes> getFirstCities() {
        String url = String.format("%s/geo/cities", geoDbConfig.baseUrl);

        ObjectMapper mapper = new ObjectMapper();
        SimpleModule module = new SimpleModule();
        module.addDeserializer(GeoDbRes.class, new GeoDbDeserializer());
        mapper.registerModule(module);

        return webClient.get()
                .uri(url)
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .retrieve()
                .bodyToMono(String.class)
                .map(body -> {
                    try {
                        return mapper.readValue(body, GeoDbRes.class);
                    } catch (IOException e) {
                        throw new RuntimeException("Failed to deserialize cities response in getFirstCities method of GeoDbClient", e);
                    }
                })
                .toFuture()
                .exceptionally(ex -> {
                    logger.error("Error requesting list of cities using geodb: {}", ex.getMessage());
                    return null;
                });
    }

    public CompletableFuture<GeoDbRes> getCitiesByQuery(String query) {
        String url = String.format("%s/geo/cities?namePrefix=%s", geoDbConfig.baseUrl, query);

        ObjectMapper mapper = new ObjectMapper();
        SimpleModule module = new SimpleModule();
        module.addDeserializer(GeoDbRes.class, new GeoDbDeserializer());
        mapper.registerModule(module);

        return webClient.get()
                .uri(url)
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .retrieve()
                .bodyToMono(String.class)
                .map(body -> {
                    try {
                        return mapper.readValue(body, GeoDbRes.class);
                    } catch (IOException e) {
                        throw new RuntimeException("Failed to deserialize cities response in getCitiesByQuery method of GeoDbClient", e);
                    }
                })
                .toFuture()
                .exceptionally(ex -> {
                    logger.error("Error requesting list of cities by query using geodb: {}", ex.getMessage());
                    return null;
                });
    }

}
