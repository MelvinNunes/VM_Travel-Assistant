package com.vm.travel.domain.services;

import com.vm.travel.dto.filters.CountryFilters;
import com.vm.travel.dto.response.CountryResDTO;
import com.vm.travel.infrastructure.exceptions.InternalServerErrorException;
import com.vm.travel.infrastructure.exceptions.NotFoundException;
import com.vm.travel.infrastructure.utils.HashMapUtils;
import com.vm.travel.integrations.restcountries.RestCountriesClient;
import com.vm.travel.integrations.restcountries.dto.RestCountriesData;
import com.vm.travel.integrations.restcountries.dto.RestCountriesResponse;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CountryService {
    private final RestCountriesClient restCountriesClient;
    private final MessageSource messageSource;
    private final Logger logger = LoggerFactory.getLogger(CountryService.class);

    /**
     * Retrieves a list of countries based on the provided filters from a cache or external API.
     *
     * @param countryFilters the filters to apply when retrieving the list of countries.
     * @return a list of {@link CountryResDTO} objects containing the details of the countries.
     * @throws InternalServerErrorException if there is an issue retrieving the country list.
     */
    @Cacheable(value = "countries", key = "#countryFilters.region == null ? 'default' : #countryFilters.region")
    public List<CountryResDTO> getAllCountries(CountryFilters countryFilters) throws InternalServerErrorException {
        CompletableFuture<RestCountriesResponse> data;

        if (countryFilters.getRegion() != null) {
            data = restCountriesClient.getAllCountriesByRegion(countryFilters.getRegion());
        } else {
            data = restCountriesClient.getAllCountries();
        }

        try {
            return data.get().restCountriesData().stream().map(this::buildCountryVM).collect(Collectors.toList());
        } catch (Exception e) {
            logger.error("Error in CountryService, getAllCountries, exception: ", e);
            throw new InternalServerErrorException(messageSource.getMessage("server.internal_error", null, LocaleContextHolder.getLocale()));
        }
    }

    /**
     * Retrieves detailed information about a country by its name from a cache or external API.
     *
     * @param countryName the name of the country to retrieve details for.
     * @return a {@link CountryResDTO} object containing the details of the country.
     * @throws InternalServerErrorException if there is an issue retrieving the country details.
     * @throws NotFoundException if no country details are found for the specified name.
     */
    @Cacheable(value = "countryDetails", key = "#countryName")
    public CountryResDTO getCountryDetailsByCountryName(String countryName) throws InternalServerErrorException, NotFoundException {
        CompletableFuture<RestCountriesResponse> data = restCountriesClient.getAllCountriesByCountryName(countryName);
        List<RestCountriesData> countries;
        try {
            countries = data.get().restCountriesData();
        } catch (Exception e) {
            logger.error("Error in CountryService, getCountryDetailsByCountryName, exception: ", e);
            throw new InternalServerErrorException(messageSource.getMessage("server.internal_error", null, LocaleContextHolder.getLocale()));
        }
        if (countries.isEmpty()) {
            throw new NotFoundException(messageSource.getMessage("countries.not_found", null, LocaleContextHolder.getLocale()));
        }
        var country = countries.get(0);
        return this.buildCountryVM(country);
    }


    public CountryResDTO buildCountryVM(RestCountriesData country) {
        return new CountryResDTO(
                country.name().common(),
                country.flags().svg(),
                HashMapUtils.getFirstKeyFromMap(country.currencies()),
                country.capital().length > 0 ? country.capital()[0] : null,
                country.region()
        );
    }


}
