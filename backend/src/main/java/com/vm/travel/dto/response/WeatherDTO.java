package com.vm.travel.dto.response;

import com.vm.travel.integrations.openweather.dto.WeatherData;

public record WeatherDTO(
        WeatherData.Weather[] weather,
        double temperature,
        double temperatureMin,
        double temperatureMax,
        double temperatureFeels,
        double temperaturePressure,
        double humidityPercentage,
        double windSpeed,
        String temperatureDate
) {
}
