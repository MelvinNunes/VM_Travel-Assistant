package com.vm.travel.infrastructure.utils;

public class WeatherConversor {
    public static double kelvinToCelsius(double temperatureInKelvin) {
        return temperatureInKelvin - 273.15;
    }

    public static double removeDecimalPlacesFromTemperature(double temp){
        return Math.round(temp);
    }

    public static double convertFromKelvinToCelsius(double temperatureInKelvin) {
        return removeDecimalPlacesFromTemperature(kelvinToCelsius(temperatureInKelvin));
    }
}
