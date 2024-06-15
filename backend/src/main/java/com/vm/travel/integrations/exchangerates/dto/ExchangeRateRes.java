package com.vm.travel.integrations.exchangerates.dto;

import java.util.List;
import java.util.Map;

public record ExchangeRateRes(
        Boolean success,
        Integer timestamp,
        String base,
        String date,
        Map<String, Double> rates
) {
}
