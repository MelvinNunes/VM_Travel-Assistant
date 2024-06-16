package com.vm.travel.integrations.restcountries.dto;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public record RestCountriesData(
        Name name,
        Flags flags,
        LinkedHashMap<String, CurrenciesData> currencies,
        String[] capital,
        String region,
        String subregion
) {
    public record NativeNameData(String official, String common){}
    public record Name(String common, String official, Map<String, NativeNameData> nativeName) {}

    public record Flags(String png, String svg, String alt) {}
    public record CurrenciesData(String name, String symbol) {}
}
