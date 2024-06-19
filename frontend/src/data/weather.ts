import { useQuery } from "@tanstack/react-query";
import { HttpClient } from "./client/http-client";
import { WeatherData } from "@/models/weather";
import { API_ENDPOINTS } from "./client/endpoints";

type WeatherRes = {
  data: WeatherData;
  message: string;
};

export function useWeather(cityName: string) {
  const { isLoading, isFetching, error, data } = useQuery({
    queryKey: ["getCityCurrentWeather", cityName],
    queryFn: () =>
      HttpClient.get<WeatherRes>(API_ENDPOINTS.CURRENT_WEATHER(cityName), null),
  });
  return {
    weather: data?.data,
    isLoading,
    isFetching,
    error,
  };
}

type WeatherForecastRes = {
  data: WeatherData[];
  message: string;
};

export function useWeatherForecast(cityName: string) {
  const { isLoading, isFetching, error, data } = useQuery({
    queryKey: ["getCityWeatherForecast", cityName],
    queryFn: () =>
      HttpClient.get<WeatherForecastRes>(
        API_ENDPOINTS.WEATHER_FORECAST(cityName),
        null
      ),
  });
  return {
    forecast: data?.data,
    isLoading,
    isFetching,
    error,
  };
}
