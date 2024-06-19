package com.vm.travel.dto.response;

import com.vm.travel.integrations.worldbank.dto.PopulationData;

import java.util.List;

public record CountryPopulationResDTO(
        String message,
        List<PopulationData> data
) {
}
