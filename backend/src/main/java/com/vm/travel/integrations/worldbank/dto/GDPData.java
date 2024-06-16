package com.vm.travel.integrations.worldbank.dto;

public record GDPData(
        Indicator indicator,
        Country country,
        String countryiso3code,
        String date,
        Double value,
        String unit,
        Double decimal,
        String obs_status
) {
    public record Indicator(String id, String value) {}
    public record Country(String id, String value) {}
}
