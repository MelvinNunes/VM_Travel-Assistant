package com.vm.travel.integrations.openweather;

import com.vm.travel.integrations.openweather.dto.WeatherData;
import com.vm.travel.integrations.openweather.dto.WeatherForecastRes;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.concurrent.CompletableFuture;

@Component
public class OpenWeatherClient {
    private final WebClient webClient;
    private final OpenWeatherConfig openWeatherConfig;
    private final Logger logger = LoggerFactory.getLogger(OpenWeatherClient.class);

    public OpenWeatherClient(WebClient.Builder webClientBuilder, OpenWeatherConfig openWeatherConfig) {
        this.webClient = webClientBuilder.build();
        this.openWeatherConfig = openWeatherConfig;
    }

    public CompletableFuture<WeatherData> getCurrentCityWeatherByCityName(String cityName) {
        return webClient.get()
                .uri(openWeatherConfig.baseUrl + "/weather?q=" + cityName + "&appid=" + openWeatherConfig.apiKey)
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .retrieve()
                .bodyToMono(WeatherData.class)
                .toFuture()
                .exceptionally(ex -> {
                    logger.error("Error requesting weather data by city name using city: {} and error: ", cityName, ex);
                    return null;
                });
    }

    public CompletableFuture<WeatherForecastRes> getCityWeatherForecastForNextFiveDays(String cityName) {
        return webClient.get() // comes ordered by date forecast
                .uri(openWeatherConfig.baseUrl + "/forecast?q=" + cityName + "&appid=" + openWeatherConfig.apiKey)
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .retrieve()
                .bodyToMono(WeatherForecastRes.class)
                .toFuture()
                .exceptionally(ex -> {
                    logger.error("Error requesting weather forecast data for next 5 days by city name using city: {} and error: ", cityName, ex);
                    return null;
                });
    }
}
