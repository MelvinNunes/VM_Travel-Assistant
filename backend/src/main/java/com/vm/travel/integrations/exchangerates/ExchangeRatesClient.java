package com.vm.travel.integrations.exchangerates;

import com.vm.travel.integrations.exchangerates.dto.ExchangeRateRes;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.concurrent.CompletableFuture;

@Component
@RequiredArgsConstructor
public class ExchangeRatesClient {
    private final Logger logger = LoggerFactory.getLogger(ExchangeRatesClient.class);
    private final WebClient webClient;
    private final ExchangeRatesConfig config;

    public CompletableFuture<ExchangeRateRes> getExchangeRateForCurrency(String currency) {
        return webClient.get()
                .uri(config.baseUrl + "/latest?base=" + currency + "&access_key=" + config.apiKey)
                .retrieve()
                .bodyToMono(ExchangeRateRes.class)
                .toFuture()
                .exceptionally(ex -> {
                    logger.error("An error occoured while fetching exchange rates for currency {}, in exchangerates api.", currency );
                    logger.error("Stacktrace: ", ex);
                    return null;
                });
    }
}
