package com.vm.travel.controllers;

import com.vm.travel.domain.services.CityService;
import com.vm.travel.dto.ResponseAPI;
import com.vm.travel.dto.filters.CityFilters;
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

@Tag(name = "Cities", description = "This collection manages cities endpoints list")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/cities")
public class CitiesController {
    private final CityService cityService;
    private final MessageSource messageSource;

    @GetMapping
    @Operation(summary = "Fetch all cities based on request params")
    @RateLimitProtection
    public ResponseEntity<ResponseAPI> getAllCities(
            @ParameterObject CityFilters cityFilters
    ) throws InternalServerErrorException {
        return ResponseEntity.status(HttpStatus.OK).body(new ResponseAPI(
                messageSource.getMessage("cities.success", null, LocaleContextHolder.getLocale()),
                cityService.getAllCities(cityFilters)
        ));
    }

    @GetMapping("/{cityName}/weather/current")
    @Operation(summary = "Gets the current weather in specific city")
    @RateLimitProtection
    public ResponseEntity<ResponseAPI> getCityCurrentWeather(@PathVariable(name = "cityName") String cityName) {
        return ResponseEntity.status(HttpStatus.OK).body(new ResponseAPI(
                messageSource.getMessage("cities.weather.current", null, LocaleContextHolder.getLocale()),
                null
        ));
    }


    @GetMapping("/{cityName}/weather/forecast")
    @Operation(summary = "Gets the weather forecast in specific city")
    @RateLimitProtection
    public ResponseEntity<ResponseAPI> getCityWeatherForecast(@PathVariable(name = "cityName") String cityName) {
        return ResponseEntity.status(HttpStatus.OK).body(new ResponseAPI(
                messageSource.getMessage("cities.weather.forecast", null, LocaleContextHolder.getLocale()),
                null
        ));
    }

    @GetMapping("/{cityName}")
    @Operation(summary = "Get specific city details using the city name")
    @RateLimitProtection
    public ResponseEntity<ResponseAPI> getCityDetails(
            @PathVariable(name = "cityName") String cityName
    ) throws InternalServerErrorException, NotFoundException {
        return ResponseEntity.status(HttpStatus.OK).body(new ResponseAPI(
                messageSource.getMessage("cities.one", null, LocaleContextHolder.getLocale()),
                cityService.getSpecificCityDetailsByCityName(cityName)
        ));
    }




}
