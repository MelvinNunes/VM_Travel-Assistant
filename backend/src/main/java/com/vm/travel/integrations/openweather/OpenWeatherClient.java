package com.vm.travel.integrations.openweather;

import com.vm.travel.integrations.openweather.dto.WeatherData;
import com.vm.travel.integrations.openweather.dto.WeatherForecastRes;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.concurrent.CompletableFuture;

@Component
@RequiredArgsConstructor
public class OpenWeatherClient {
    private final WebClient webClient;
    private final OpenWeatherConfig openWeatherConfig;
    private final Logger logger = LoggerFactory.getLogger(OpenWeatherClient.class);

    public CompletableFuture<WeatherData> getCurrentCityWeatherByCityName(String cityName, String lang) {
        String url = String.format("%s/weather?q=%s&appid=%s&lang=%s", openWeatherConfig.baseUrl, cityName, openWeatherConfig.apiKey, lang);

        return webClient.get()
                .uri(url)
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .retrieve()
                .bodyToMono(WeatherData.class)
                .toFuture()
                .exceptionally(ex -> {
                    logger.error("Error requesting weather data by city name using city: {} and error: ", cityName, ex);
                    return null;
                });
    }

    public CompletableFuture<WeatherForecastRes> getCityWeatherForecastForNextFiveDays(String cityName, String lang) {
        String url = String.format("%s/forecast?q=%s&appid=%s&lang=%s", openWeatherConfig.baseUrl, cityName, openWeatherConfig.apiKey, lang);

        return webClient.get()
                .uri(url)
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
