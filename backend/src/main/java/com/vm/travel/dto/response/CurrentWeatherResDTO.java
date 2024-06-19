package com.vm.travel.dto.response;

public record CurrentWeatherResDTO(
        String message,
        WeatherDTO data
) {
}
