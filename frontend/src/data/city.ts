import { City } from "@/models/city";
import { HttpClient } from "./client/http-client";
import { API_ENDPOINTS } from "./client/endpoints";
import { useQuery } from "@tanstack/react-query";

type CitiesRes = {
  message: string;
  data: City[];
};

export function useCities({ name }: { name?: string }) {
  const { isLoading, error, data } = useQuery({
    queryKey: ["getAllCities"],
    queryFn: () => HttpClient.get<CitiesRes>(API_ENDPOINTS.CITIES(name), null),
  });
  return {
    cities: data,
    isLoading,
    error,
  };
}

type CityRes = {
  message: string;
  data: City;
};

export function useCity(name: string) {
  const { isLoading, error, data } = useQuery({
    queryKey: ["getCityDetails", name],
    queryFn: () => HttpClient.get<CityRes>(API_ENDPOINTS.CITY(name), null),
  });
  return {
    city: data,
    isLoading,
    error,
  };
}
