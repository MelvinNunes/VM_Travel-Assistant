package com.vm.travel.integrations.geodb.dto;

public record GeoDbCity(
        int id,
        String wikiDataId,
        String type,
        String city,
        String name,
        String country,
        String countryCode,
        String region,
        String regionCode,
        String regionWdId,
        Double latitude,
        Double longitude,
        Integer population,
        Double distance,
        String placeType
) {
}
