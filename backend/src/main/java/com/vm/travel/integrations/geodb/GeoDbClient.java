package com.vm.travel.integrations.geodb;

import com.vm.travel.integrations.geodb.dto.GeoDbRes;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.concurrent.CompletableFuture;

@Component
@RequiredArgsConstructor
public class GeoDbClient {
    private final WebClient webClient;
    private final GeoDbConfig geoDbConfig;
    private final Logger logger = LoggerFactory.getLogger(GeoDbClient.class);

    public CompletableFuture<GeoDbRes> getCitiesByQuery(String query) {
        return webClient.get()
                .uri(geoDbConfig.baseUrl + "/geo/cities?namePrefix=" + query)
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .retrieve()
                .bodyToMono(GeoDbRes.class)
                .toFuture()
                .exceptionally(ex -> {
                    logger.error("Error requesting list of cities by query using geodb: {}", ex.getMessage());
                    return null;
                });
    }

}
