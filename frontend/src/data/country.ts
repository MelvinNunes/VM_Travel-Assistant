import { useQuery } from "@tanstack/react-query";
import { API_ENDPOINTS } from "./client/endpoints";
import { HttpClient } from "./client/http-client";
import { Country } from "@/models/country";

type CountryRes = {
  data: Country;
  message: string;
};

export function useCountry(countryName: string) {
  const { isLoading, isFetching, error, data } = useQuery({
    queryKey: ["getCountryDetails", countryName],
    queryFn: () =>
      HttpClient.get<CountryRes>(API_ENDPOINTS.COUNTRY(countryName), null),
  });
  return {
    country: data?.data,
    isLoading,
    isFetching,
    error,
  };
}
