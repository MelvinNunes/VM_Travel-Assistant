package com.vm.travel.dto.response;

import com.vm.travel.integrations.exchangerates.dto.ExchangeRateRes;

public record CountryExchangeRatesResDTO(
        String message,
        ExchangeRateRes data
) {
}
