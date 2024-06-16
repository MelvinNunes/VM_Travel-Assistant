package com.vm.travel.infrastructure.utils;

public class WeatherConversor {
    public static double kelvinToCelsius(double temperatureInKelvin) {
        return temperatureInKelvin - 273.15;
    }
}
