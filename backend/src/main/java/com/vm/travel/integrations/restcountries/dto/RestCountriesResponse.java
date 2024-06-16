package com.vm.travel.integrations.restcountries.dto;

import java.util.List;

public record RestCountriesResponse(
        List<RestCountriesData> restCountriesData
) {
}
