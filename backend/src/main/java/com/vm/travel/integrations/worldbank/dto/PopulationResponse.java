package com.vm.travel.integrations.worldbank.dto;

import java.util.List;

public record PopulationResponse(
        List<PopulationData> populationData
) {
}
