package com.vm.travel.dto.response;

public record CityResDTO(
        int id,
        String type,
        String city,
        String name,
        String country,
        String countryCode,
        String region,
        String regionCode,
        Double latitude,
        Double longitude,
        Integer population,
        String placeType
) {
}
