package com.vm.travel.integrations.openweather.dto;

public record WeatherData(
        Coord coord,
        Weather[] weather,
        String base,
        Main main,
        int visibility,
        Wind wind,
        Clouds clouds,
        long dt,
        Sys sys,
        int timezone,
        int id,
        String name,
        int cod,
        String dtTxt
) {
    public record Coord(double lon, double lat) {}
    public record Weather(int id, String main, String description, String icon) {}
    public record Main(Double temp, Double feels_like, Double temp_min, Double temp_max, int pressure, int humidity, int sea_level, int grnd_level) {}
    public record Wind(Double speed, int deg, Double gust) {}
    public record Clouds(int all) {}
    public record Sys(String country, long sunrise, long sunset) {}
}

