package com.vm.travel.integrations.worldbank.dto;

import java.util.List;

public record GDPResponse(
        List<GDPData> gdpData
) {
}
