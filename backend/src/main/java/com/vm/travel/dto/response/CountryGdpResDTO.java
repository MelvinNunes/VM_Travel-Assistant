package com.vm.travel.dto.response;

import com.vm.travel.integrations.worldbank.dto.GDPData;

import java.util.List;

public record CountryGdpResDTO(
        String message,
        List<GDPData> data
) {
}
