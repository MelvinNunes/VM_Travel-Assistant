import { City } from "@/models/city";
import { HttpClient } from "./client/http-client";
import { API_ENDPOINTS } from "./client/endpoints";
import { useQuery } from "@tanstack/react-query";

type CitiesRes = {
  message: string;
  data: City[];
};

export function useCities({ name }: { name?: string }) {
  const { isLoading, error, data, isPending, isFetching } = useQuery({
    queryKey: ["getAllCities"],
    queryFn: () => HttpClient.get<CitiesRes>(API_ENDPOINTS.CITIES(name), null),
  });
  return {
    cities: data?.data ?? [],
    isLoading,
    isPending,
    isFetching,
    error,
  };
}

type CityRes = {
  message: string;
  data: City;
};

export function useCity(cityName?: string) {
  const { isLoading, isFetching, error, data } = useQuery({
    queryKey: ["getCityDetails", cityName],
    queryFn: () => HttpClient.get<CityRes>(API_ENDPOINTS.CITY(cityName), null),
  });
  return {
    city: data?.data,
    isLoading,
    isFetching,
    error,
  };
}
