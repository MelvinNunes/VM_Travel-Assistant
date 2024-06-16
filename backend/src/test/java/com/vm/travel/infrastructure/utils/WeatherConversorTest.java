package com.vm.travel.infrastructure.utils;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class WeatherConversorTest {
    private static final double DELTA = 1e-9;

    @Test
    @DisplayName("Success case - Check if 0 kelvin equals -273.15 Celsius")
    void test_zero_kelvin_success() {
        double kelvinTemperature = 0.0;
        double expectedCelsiusTemperature = -273.15;
        assertEquals(expectedCelsiusTemperature, WeatherConversor.kelvinToCelsius(kelvinTemperature), DELTA);
    }

    @Test
    @DisplayName("Success case - Check if 273.15 kelvin equals 0 Celsius")
    void test_zero_celsius_success() {
        double kelvinTemperature = 273.15;
        double expectedCelsiusTemperature = 0.0;
        assertEquals(expectedCelsiusTemperature, WeatherConversor.kelvinToCelsius(kelvinTemperature), DELTA);
    }

    @Test
    @DisplayName("Success case - Check if 300.0 kelvin equals 26.85 Celsius")
    void test_final_celsius_success() {
        double kelvinTemperature = 300.0;
        double expectedCelsiusTemperature = 26.85;
        assertEquals(expectedCelsiusTemperature,
                WeatherConversor.kelvinToCelsius(kelvinTemperature),
                DELTA);
    }


    @Test
    @DisplayName("Success case - Check if 26.7 celsius is rounded to 27")
    void test_round_temp_success() {
        double celsiusTemp = 26.7;
        double expectedRoundedTemp = 27;
        assertEquals(expectedRoundedTemp, WeatherConversor.removeDecimalPlacesFromTemperature(celsiusTemp));
    }

    @Test
    @DisplayName("Success case - Check if 26.4 celsius is rounded to 26")
    void test_round_temp_success_2() {
        double celsiusTemp = 26.4;
        double expectedRoundedTemp = 26;
        assertEquals(expectedRoundedTemp, WeatherConversor.removeDecimalPlacesFromTemperature(celsiusTemp));
    }


    @Test
    @DisplayName("Success case - Check if 25.5 celsius is rounded to 26")
    void test_round_temp_success_3() {
        double celsiusTemp = 25.5;
        double expectedRoundedTemp = 26;
        assertEquals(expectedRoundedTemp, WeatherConversor.removeDecimalPlacesFromTemperature(celsiusTemp));
    }
}