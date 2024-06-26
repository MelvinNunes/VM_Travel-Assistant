package com.vm.travel.controllers;

import com.vm.travel.domain.services.CityService;
import com.vm.travel.dto.ResponseAPI;
import com.vm.travel.dto.filters.CityFilters;
import com.vm.travel.dto.response.CitiesResDTO;
import com.vm.travel.dto.response.CityResDTO;
import com.vm.travel.dto.response.CurrentWeatherResDTO;
import com.vm.travel.dto.response.WeatherForecastResDTO;
import com.vm.travel.infrastructure.config.ratelimit.RateLimitProtection;
import com.vm.travel.infrastructure.exceptions.InternalServerErrorException;
import com.vm.travel.infrastructure.exceptions.NotFoundException;
import com.vm.travel.infrastructure.exceptions.UnprocessableEntityException;
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
    public ResponseEntity<CitiesResDTO> getAllCities(
            @ParameterObject CityFilters cityFilters
    ) throws InternalServerErrorException, UnprocessableEntityException {
        if (cityFilters.getName() != null && cityFilters.getCountryCode() != null) {
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(new CitiesResDTO(
                    messageSource.getMessage("cities.unprocessable", null, LocaleContextHolder.getLocale()),
                    null
            ));
        }
        return ResponseEntity.status(HttpStatus.OK).body(new CitiesResDTO(
                messageSource.getMessage("cities.success", null, LocaleContextHolder.getLocale()),
                cityService.getAllCities(cityFilters)
        ));
    }

    @GetMapping("/{cityName}/weather/current")
    @Operation(summary = "Gets the current weather in specific city")
    @RateLimitProtection
    public ResponseEntity<CurrentWeatherResDTO> getCityCurrentWeather(@PathVariable(name = "cityName") String cityName) throws InternalServerErrorException, UnprocessableEntityException {
        String currentLang = LocaleContextHolder.getLocale().getLanguage();
        return ResponseEntity.status(HttpStatus.OK).body(new CurrentWeatherResDTO(
                messageSource.getMessage("cities.weather.current", null, LocaleContextHolder.getLocale()),
                cityService.getCityCurrentWeatherByCityName(cityName, currentLang)
        ));
    }


    @GetMapping("/{cityName}/weather/forecast")
    @Operation(summary = "Gets the weather forecast in specific city")
    @RateLimitProtection
    public ResponseEntity<WeatherForecastResDTO> getCityWeatherForecast(@PathVariable(name = "cityName") String cityName) throws InternalServerErrorException, UnprocessableEntityException {
        String currentLang = LocaleContextHolder.getLocale().getLanguage();
        return ResponseEntity.status(HttpStatus.OK).body(new WeatherForecastResDTO(
                messageSource.getMessage("cities.weather.forecast", null, LocaleContextHolder.getLocale()),
                cityService.getCityWeatherForecastByCityName(cityName, currentLang)
        ));
    }

    @GetMapping("/{cityName}")
    @Operation(summary = "Get specific city details using the city name")
    @RateLimitProtection
    public ResponseEntity<CityResDTO> getCityDetails(
            @PathVariable(name = "cityName") String cityName
    ) throws InternalServerErrorException, NotFoundException {
        return ResponseEntity.status(HttpStatus.OK).body(new CityResDTO(
                messageSource.getMessage("cities.one", null, LocaleContextHolder.getLocale()),
                cityService.getSpecificCityDetailsByCityName(cityName)
        ));
    }


}
