package com.vm.travel.domain.services;

import com.vm.travel.dto.filters.CountryFilters;
import com.vm.travel.dto.response.CityResDTO;
import com.vm.travel.dto.response.CountryResDTO;
import com.vm.travel.infrastructure.exceptions.InternalServerErrorException;
import com.vm.travel.infrastructure.exceptions.NotFoundException;
import com.vm.travel.infrastructure.exceptions.UnprocessableEntityException;
import com.vm.travel.infrastructure.utils.HashMapUtils;
import com.vm.travel.integrations.exchangerates.ExchangeRatesClient;
import com.vm.travel.integrations.exchangerates.dto.ExchangeRateRes;
import com.vm.travel.integrations.restcountries.RestCountriesClient;
import com.vm.travel.integrations.restcountries.dto.RestCountriesData;
import com.vm.travel.integrations.restcountries.dto.RestCountriesResponse;
import com.vm.travel.integrations.worldbank.WorldBankApiClient;
import com.vm.travel.integrations.worldbank.dto.GDPData;
import com.vm.travel.integrations.worldbank.dto.GDPResponse;
import com.vm.travel.integrations.worldbank.dto.PopulationData;
import com.vm.travel.integrations.worldbank.dto.PopulationResponse;
import lombok.RequiredArgsConstructor;
import org.aspectj.weaver.ast.Not;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CountryService {
    private final RestCountriesClient restCountriesClient;
    private final WorldBankApiClient worldBankApiClient;
    private final ExchangeRatesClient exchangeRatesClient;
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
    public List<CountryResDTO> getAllCountries(CountryFilters countryFilters) throws InternalServerErrorException, NotFoundException {
        CompletableFuture<RestCountriesResponse> restCountriesResponseCompletableFuture;
        RestCountriesResponse data;
        if (countryFilters.getRegion() != null) {
            restCountriesResponseCompletableFuture = restCountriesClient.getAllCountriesByRegion(countryFilters.getRegion());
        } else {
            restCountriesResponseCompletableFuture = restCountriesClient.getAllCountries();
        }
        try {
            data = restCountriesResponseCompletableFuture.get();
        } catch (Exception e) {
            logger.error("Error in CountryService, getAllCountries, exception: ", e);
            throw new InternalServerErrorException(messageSource.getMessage("server.internal_error", null, LocaleContextHolder.getLocale()));
        }
        if (data == null) {
            throw new NotFoundException(messageSource.getMessage("countries.region.not_found", null, LocaleContextHolder.getLocale()));
        }
        return data.restCountriesData().stream().map(this::buildCountryVM).collect(Collectors.toList());
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
        RestCountriesResponse response;
        try {
            response = data.get();
        } catch (Exception e) {
            logger.error("Error in CountryService, getCountryDetailsByCountryName, exception: ", e);
            throw new InternalServerErrorException(messageSource.getMessage("server.internal_error", null, LocaleContextHolder.getLocale()));
        }
        if (response == null) {
            throw new NotFoundException(messageSource.getMessage("countries.not_found", null, LocaleContextHolder.getLocale()));
        }
        List<RestCountriesData> countries = response.restCountriesData();
        if (countries.isEmpty()) {
            throw new NotFoundException(messageSource.getMessage("countries.not_found", null, LocaleContextHolder.getLocale()));
        }
        var country = countries.get(0);
        return this.buildCountryVM(country);
    }

    /**
     * Retrieves exchange rate details for a country by its name from a cache or external API.
     *
     * @param countryName the name of the country to retrieve exchange rates for.
     * @return an {@link ExchangeRateRes} object containing the exchange rate details.
     * @throws InternalServerErrorException if there is an issue retrieving the exchange rate details.
     * @throws NotFoundException if no exchange rate details are found for the specified country.
     */
    @Cacheable(value = "countryExchangeRatesByCountryName", key = "#countryName")
    public ExchangeRateRes getCountryExchangeRatesByCountryName(String countryName) throws InternalServerErrorException, NotFoundException {
        String defaultCurrency = "EUR"; // free version only accepts EUR😅
        CountryResDTO country = getCountryDetailsByCountryName(countryName); // had to get the country to get the currency
        try {
            return fetchExchangeRateForCurrency(country.currency()).orElseGet(() -> {
                try {
                    return fetchExchangeRateForCurrency(defaultCurrency)
                            .orElseThrow(() -> new InternalServerErrorException(messageSource.getMessage("server.internal_error", null, LocaleContextHolder.getLocale())));
                } catch (InternalServerErrorException e) {
                    throw new RuntimeException(e);
                }
            });
        } catch (Exception e) {
            logger.error("Error fetching exchange rate for country: {}", countryName, e);
            throw new InternalServerErrorException(messageSource.getMessage("server.internal_error", null, LocaleContextHolder.getLocale()));
        }
    }

    private Optional<ExchangeRateRes> fetchExchangeRateForCurrency(String currency) {
        try {
            var data = exchangeRatesClient.getExchangeRateForCurrency(currency).get();
            if (!data.success()) {
                throw new UnprocessableEntityException(messageSource.getMessage("countries.exchanges.error", null, LocaleContextHolder.getLocale()));
            }
            return Optional.of(data);
        } catch (Exception e) {
            logger.error("Error fetching exchange rate for default currency: {}", currency, e);
            return Optional.empty();
        }
    }

    /**
     * Retrieves population data for a country by its country code from a cache or external API.
     *
     * @param countryCode the code of the country to retrieve population data for.
     * @return a list of {@link PopulationData} objects containing the population data for the country.
     * @throws InternalServerErrorException if there is an issue retrieving the population data.
     * @throws NotFoundException if no population data is found for the specified country code.
     */
    @Cacheable(value = "countryPopulationDataByCountryCode", key = "#countryCode")
    public List<PopulationData> getCountryPopulationDataByCountryCode(String countryCode) throws InternalServerErrorException, NotFoundException {
       var populationCompletableFuture = worldBankApiClient.getCountryPopulationDataByCountryCode(countryCode);
        PopulationResponse data;
       try {
           data = populationCompletableFuture.get();
       } catch (Exception e) {
           logger.error("Error in CountryService, getCountryPopulationDataByCountryCode, exception: ", e);
           throw new InternalServerErrorException(messageSource.getMessage("server.internal_error", null, LocaleContextHolder.getLocale()));
       }
        if (data == null) {
            throw new NotFoundException(messageSource.getMessage("countries.pupulation.not_found", null, LocaleContextHolder.getLocale()));
        }
        return data.populationData();
    }

    /**
     * Retrieves GDP data for a country by its country code from a cache or external API.
     *
     * @param countryCode the code of the country to retrieve GDP data for.
     * @return a list of {@link GDPData} objects containing the GDP data for the country.
     * @throws InternalServerErrorException if there is an issue retrieving the GDP data.
     * @throws NotFoundException if no GDP data is found for the specified country code.
     */
    @Cacheable(value = "countryGdpDataByCountryCode", key = "#countryCode")
    public List<GDPData> getCountryGdpDataByCountryCode(String countryCode) throws InternalServerErrorException, NotFoundException {
        var populationCompletableFuture = worldBankApiClient.getCountryGdpDataByCountryCode(countryCode);
        GDPResponse response;
        try {
            response = populationCompletableFuture.get();
        } catch (Exception e) {
            logger.error("Error in CountryService, getCountryGdpDataByCountryCode, exception: ", e);
            throw new InternalServerErrorException(messageSource.getMessage("server.internal_error", null, LocaleContextHolder.getLocale()));
        }
        if (response == null) {
            throw new NotFoundException(messageSource.getMessage("countries.gdp.not_found", null, LocaleContextHolder.getLocale()));
        }
        return response.gdpData();
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
