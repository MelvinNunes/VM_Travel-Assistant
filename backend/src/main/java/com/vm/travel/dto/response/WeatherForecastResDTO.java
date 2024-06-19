package com.vm.travel.dto.response;

import java.util.List;

public record WeatherForecastResDTO(
        String message,
        List<WeatherDTO> data
) {
}
