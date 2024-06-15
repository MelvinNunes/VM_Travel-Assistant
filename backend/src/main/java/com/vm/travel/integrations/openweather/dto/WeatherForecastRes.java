package com.vm.travel.integrations.openweather.dto;

import java.util.List;

public record WeatherForecastRes(
        List<WeatherData> list
) {
}
