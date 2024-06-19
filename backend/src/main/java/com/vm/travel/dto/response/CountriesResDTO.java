package com.vm.travel.dto.response;

import java.util.List;

public record CountriesResDTO(
        String message,
        List<CountryDTO> data
) {
}
