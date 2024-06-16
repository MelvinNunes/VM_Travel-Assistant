package com.vm.travel.domain.services;

import com.vm.travel.dto.filters.CityFilters;
import com.vm.travel.dto.response.CityResDTO;
import com.vm.travel.infrastructure.exceptions.InternalServerErrorException;
import com.vm.travel.infrastructure.exceptions.NotFoundException;
import com.vm.travel.integrations.geodb.GeoDbClient;
import com.vm.travel.integrations.geodb.dto.GeoDbRes;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CityService {
    private final GeoDbClient geoDbClient;
    private final MessageSource messageSource;

    /**
     * Retrieves a list of all cities based on the provided filters.
     *
     * @param cityFilters the filters to apply when retrieving the list of cities.
     * @return a list of {@link CityResDTO} objects containing the details of the cities.
     * @throws InternalServerErrorException if there is an issue retrieving the city details.
     */
    @Cacheable(value = "cities", key = "#cityFilters.name == null ? 'default' : #cityFilters.name")
    public List<CityResDTO> getAllCities(CityFilters cityFilters) throws InternalServerErrorException {
        CompletableFuture<GeoDbRes> citiesFuture = null;
        List<CityResDTO> cities;

        if (cityFilters.getName() == null) {
            citiesFuture = geoDbClient.getFirstCities();
        }
        if (cityFilters.getName() != null) {
            citiesFuture = geoDbClient.getCitiesByQuery(cityFilters.getName());
        }

        try {
           cities = this.getCitiesFromFuture(citiesFuture);
        } catch (Exception e) {
            throw new InternalServerErrorException(messageSource.getMessage("server.internal_error", null, LocaleContextHolder.getLocale()));
        }
        return cities;
    }

    /**
     * Retrieves detailed information about a specific city by its name.
     *
     * @param cityName the name of the city to retrieve details for.
     * @return a {@link CityResDTO} object containing the details of the first city.
     * @throws InternalServerErrorException if there is an issue retrieving the city details.
     * @throws NotFoundException if no city details are found for the specified name.
     */
    public CityResDTO getSpecificCityDetailsByCityName(String cityName) throws InternalServerErrorException, NotFoundException {
        CompletableFuture<GeoDbRes> citiesFuture = geoDbClient.getCitiesByQuery(cityName);
        List<CityResDTO> cities;
        try {
            cities = this.getCitiesFromFuture(citiesFuture);
        } catch (Exception e) {
            throw new InternalServerErrorException(messageSource.getMessage("server.internal_error", null, LocaleContextHolder.getLocale()));
        }
        if (cities.isEmpty()) {
            throw new NotFoundException(messageSource.getMessage("cities.not_found", new Object[]{cityName}, LocaleContextHolder.getLocale()));
        }
        return cities.get(0);
    }


    private List<CityResDTO> getCitiesFromFuture(CompletableFuture<GeoDbRes> completableFuture) throws ExecutionException, InterruptedException {
        return completableFuture.get().data().stream().map(city -> new CityResDTO(
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

}
