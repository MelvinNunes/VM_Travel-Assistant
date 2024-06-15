package com.vm.travel.integrations.geodb.dto;

import java.util.List;

public record GeoDbRes(
        List<GeoDbCity> data
) {
}
