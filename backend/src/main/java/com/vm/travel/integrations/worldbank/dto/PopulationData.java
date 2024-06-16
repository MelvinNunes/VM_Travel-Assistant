package com.vm.travel.integrations.worldbank.dto;

public record PopulationData(
        IndicatorRecord indicator,
        CountryRecord country,
        String countryiso3code,
        String date,
        Double value,
        String unit,
        Double decimal,
        String obs_status
) {
    public record IndicatorRecord(String id, String value) {}
    public record CountryRecord(String id, String value) {}
}
