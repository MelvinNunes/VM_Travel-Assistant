package com.vm.travel.domain.services;

import com.vm.travel.dto.filters.CityFilters;
import com.vm.travel.dto.response.CityDTO;
import com.vm.travel.dto.response.WeatherDTO;
import com.vm.travel.infrastructure.exceptions.InternalServerErrorException;
import com.vm.travel.infrastructure.exceptions.NotFoundException;
import com.vm.travel.infrastructure.exceptions.UnprocessableEntityException;
import com.vm.travel.infrastructure.utils.WeatherConversor;
import com.vm.travel.integrations.geodb.GeoDbClient;
import com.vm.travel.integrations.geodb.dto.GeoDbRes;
import com.vm.travel.integrations.openweather.OpenWeatherClient;
import com.vm.travel.integrations.openweather.dto.WeatherData;
import com.vm.travel.integrations.openweather.dto.WeatherForecastRes;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CityService {
    private final GeoDbClient geoDbClient;
    private final OpenWeatherClient weatherClient;
    private final MessageSource messageSource;
    private final Logger logger = LoggerFactory.getLogger(CityService.class);

    /**
     * Retrieves a list of all cities based on the provided filters from a cache or external API.
     *
     * @param cityFilters the filters to apply when retrieving the list of cities.
     * @return a list of {@link CityDTO} objects containing the details of the cities.
     * @throws InternalServerErrorException if there is an issue retrieving the city details.
     */
    @Cacheable(value = "cities", key = "#cityFilters.name != null ? #cityFilters.name : #cityFilters.countryCode != null ? 'country:' + #cityFilters.countryCode : 'default'")
    public List<CityDTO> getAllCities(CityFilters cityFilters) throws InternalServerErrorException {
        CompletableFuture<GeoDbRes> citiesFuture;
        List<CityDTO> cities;
        if (cityFilters.getName() != null) {
            citiesFuture = geoDbClient.getCitiesByQuery(cityFilters.getName());
        } else if (cityFilters.getCountryCode() != null) {
            citiesFuture = geoDbClient.getCitiesByCountryCode(cityFilters.getCountryCode());
        } else {
            citiesFuture = geoDbClient.getFirstCities();
        }
        try {
            cities = this.getCitiesFromFuture(citiesFuture);
        } catch (Exception e) {
            logger.error("Unknow error in CityService, getAllCities, exception: ", e);
            throw new InternalServerErrorException(messageSource.getMessage("server.internal_error", null, LocaleContextHolder.getLocale()));
        }
        return cities;
    }

    /**
     * Retrieves detailed information about a specific city by its name from a cache or external API.
     *
     * @param cityName the name of the city to retrieve details for.
     * @return a {@link CityDTO} object containing the details of the first city.
     * @throws InternalServerErrorException if there is an issue retrieving the city details.
     * @throws NotFoundException            if no city details are found for the specified name.
     */
    @Cacheable(value = "cityDetails", key = "#cityName")
    public CityDTO getSpecificCityDetailsByCityName(String cityName) throws InternalServerErrorException, NotFoundException {
        CompletableFuture<GeoDbRes> citiesFuture = geoDbClient.getCitiesByQuery(cityName);
        List<CityDTO> cities;
        try {
            cities = this.getCitiesFromFuture(citiesFuture);
        } catch (Exception e) {
            logger.error("Unknow error in CityService, getSpecificCityDetailsByCityName, exception: ", e);
            throw new InternalServerErrorException(messageSource.getMessage("server.internal_error", null, LocaleContextHolder.getLocale()));
        }
        if (cities.isEmpty()) {
            throw new NotFoundException(messageSource.getMessage("cities.not_found", new Object[]{cityName}, LocaleContextHolder.getLocale()));
        }
        return cities.get(0);
    }

    /**
     * Retrieves the current weather details of a city by its name from a cache or external API.
     *
     * @param cityName the name of the city to retrieve weather details for.
     * @param lang the current language of the API.
     * @return a {@link WeatherData} object containing the current weather details of the city.
     * @throws InternalServerErrorException if there is an issue retrieving the weather details.
     */
    @Cacheable(value = "currentCityWeatherDetails", key = "#cityName + '_' + #lang")
    public WeatherDTO getCityCurrentWeatherByCityName(String cityName, String lang) throws InternalServerErrorException, UnprocessableEntityException {
        CompletableFuture<WeatherData> weatherFuture = weatherClient.getCurrentCityWeatherByCityName(cityName, lang);
        WeatherData data;
        try {
            data = weatherFuture.get();
        } catch (Exception e) {
            logger.error("Unknow error in CityService, getCityCurrentWeatherByCityName, exception: ", e);
            throw new InternalServerErrorException(messageSource.getMessage("cities.weather.error", null, LocaleContextHolder.getLocale()));
        }
        if (data == null) {
            throw new UnprocessableEntityException(messageSource.getMessage("cities.weather.unprocessable", null, LocaleContextHolder.getLocale()));
        }
        return this.buildWeatherResDTO(data);
    }

    /**
     * Retrieves the weather forecast details of a city by its name for the next five days from a cache or external API.
     *
     * @param cityName the name of the city to retrieve weather forecast details for.
     * @param lang the current language of the API.
     * @return a list of {@link WeatherData} objects containing the weather forecast details for the city.
     * @throws InternalServerErrorException if there is an issue retrieving the weather forecast details.
     */
    @Cacheable(value = "currentCityWeatherForecastDetails", key = "#cityName + '_' + #lang")
    public List<WeatherDTO> getCityWeatherForecastByCityName(String cityName, String lang) throws InternalServerErrorException, UnprocessableEntityException {
        CompletableFuture<WeatherForecastRes> weatherFuture = weatherClient.getCityWeatherForecastForNextFiveDays(cityName, lang);
        WeatherForecastRes response;
        try {
            response = weatherFuture.get();
        } catch (Exception e) {
            logger.error("Unknow error in CityService, getCityWeatherForecastByCityName, exception: ", e);
            throw new InternalServerErrorException(messageSource.getMessage("cities.weather.error", null, LocaleContextHolder.getLocale()));
        }
        if (response == null) {
            throw new UnprocessableEntityException(messageSource.getMessage("cities.weather.unprocessable", null, LocaleContextHolder.getLocale()));
        }
        return response.list().stream().map(this::buildWeatherResDTO).collect(Collectors.toList());
    }

    private List<CityDTO> getCitiesFromFuture(CompletableFuture<GeoDbRes> completableFuture) throws InternalServerErrorException, NotFoundException {
        GeoDbRes res;
        try {
            res = completableFuture.get();
        } catch (Exception e) {
            logger.error("Error in getCitiesFromFuture, probably the issue is with the connection: ", e);
            throw new InternalServerErrorException(messageSource.getMessage("server.internal_error", null, LocaleContextHolder.getLocale()));
        }
        if (res == null) {
            throw new NotFoundException(messageSource.getMessage("cities.not_found", null, LocaleContextHolder.getLocale()));
        }
        return res.data().stream().map(city -> new CityDTO(
                city.id(),
                city.type(),
                city.city(),
                city.name(),
                city.country(),
                city.countryCode(),
                city.region(),
                city.regionCode(),
                city.latitude(),
                city.longitude(),
                city.population(),
                city.placeType()
        )).collect(Collectors.toList());
    }

    private WeatherDTO buildWeatherResDTO(WeatherData weatherData) {
        return new WeatherDTO(
                weatherData.weather(),
                WeatherConversor.convertFromKelvinToCelsius(weatherData.main().temp()),
                WeatherConversor.convertFromKelvinToCelsius(weatherData.main().temp_min()),
                WeatherConversor.convertFromKelvinToCelsius(weatherData.main().temp_max()),
                WeatherConversor.convertFromKelvinToCelsius(weatherData.main().feels_like()),
                weatherData.main().pressure(),
                weatherData.main().humidity(),
                weatherData.wind().speed(),
                weatherData.dtTxt()
        );
    }

}
