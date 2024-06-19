package com.vm.travel.dto.response;

import java.util.List;

public record CitiesResDTO(
        String message,
        List<CityDTO> data
) {
}
