package com.vm.travel.controllers;

import com.vm.travel.domain.services.CountryService;
import com.vm.travel.dto.filters.CountryFilters;
import com.vm.travel.dto.response.*;
import com.vm.travel.infrastructure.config.ratelimit.RateLimitProtection;
import com.vm.travel.infrastructure.exceptions.InternalServerErrorException;
import com.vm.travel.infrastructure.exceptions.NotFoundException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Countries", description = "This collection manages countries endpoints list")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/countries")
public class CountriesController {
    private final MessageSource messageSource;
    private final CountryService countryService;

    @GetMapping
    @Operation(summary = "Fetch all countries based on request params")
    @RateLimitProtection
    public ResponseEntity<CountriesResDTO> getAllCountries(
            @ParameterObject CountryFilters countryFilters
    ) throws InternalServerErrorException, NotFoundException {
        return ResponseEntity.status(HttpStatus.OK).body(new CountriesResDTO(
                messageSource.getMessage("countries.success", null, LocaleContextHolder.getLocale()),
                countryService.getAllCountries(countryFilters)
        ));
    }

    @GetMapping("/{countryCode}/population")
    @Operation(summary = "Get country population data based on country's code")
    @RateLimitProtection
    public ResponseEntity<CountryPopulationResDTO> getCountryPopulationData(
            @PathVariable(name = "countryCode") String countryCode
    ) throws InternalServerErrorException, NotFoundException {
        return ResponseEntity.status(HttpStatus.OK).body(new CountryPopulationResDTO(
                messageSource.getMessage("countries.pupulation.success", null, LocaleContextHolder.getLocale()),
                countryService.getCountryPopulationDataByCountryCode(countryCode)
        ));
    }

    @GetMapping("/{countryCode}/gdp")
    @Operation(summary = "Get country gdp data based on country's code")
    @RateLimitProtection
    public ResponseEntity<CountryGdpResDTO> getCountryGdpData(
            @PathVariable(name = "countryCode") String countryCode
    ) throws InternalServerErrorException, NotFoundException {
        return ResponseEntity.status(HttpStatus.OK).body(new CountryGdpResDTO(
                messageSource.getMessage("countries.gdp.success", null, LocaleContextHolder.getLocale()),
                countryService.getCountryGdpDataByCountryCode(countryCode)
        ));
    }

    @GetMapping("/{countryName}/exchange-rates")
    @Operation(summary = "Get specific country exchange rates using the country name")
    @RateLimitProtection
    public ResponseEntity<CountryExchangeRatesResDTO> getCityExchangeRates(
            @PathVariable(name = "countryName") String countryName
    ) throws InternalServerErrorException, NotFoundException {
        return ResponseEntity.status(HttpStatus.OK).body(new CountryExchangeRatesResDTO(
                messageSource.getMessage("countries.exchanges.success", null, LocaleContextHolder.getLocale()),
                countryService.getCountryExchangeRatesByCountryName(countryName)
        ));
    }

    @GetMapping("/{countryName}")
    @Operation(summary = "Get country details based on country's name")
    @RateLimitProtection
    public ResponseEntity<CountryResDTO> getCountryDetails(
            @PathVariable(name = "countryName") String countryName
    ) throws InternalServerErrorException, NotFoundException {
        return ResponseEntity.status(HttpStatus.OK).body(new CountryResDTO(
                messageSource.getMessage("countries.one", null, LocaleContextHolder.getLocale()),
                countryService.getCountryDetailsByCountryName(countryName)
        ));
    }

}
