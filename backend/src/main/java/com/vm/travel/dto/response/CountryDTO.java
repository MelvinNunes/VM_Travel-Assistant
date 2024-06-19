package com.vm.travel.dto.response;

public record CountryDTO(
        String name,
        String flag,
        String currency,
        String capital,
        String region
) {
}
