import { ExchangeRates } from "@/models/currency";
import { useQuery } from "@tanstack/react-query";
import { API_ENDPOINTS } from "./client/endpoints";
import { HttpClient } from "./client/http-client";

type CurrencyRes = {
  data: ExchangeRates;
  message: string;
};

export function useCountryCurrencies(countryName: string) {
  const { isLoading, isFetching, error, data } = useQuery({
    queryKey: ["getCountryCurrencyDetails", countryName],
    queryFn: () =>
      HttpClient.get<CurrencyRes>(API_ENDPOINTS.CURRENCY(countryName), null),
  });
  return {
    currencies: data?.data,
    isLoading,
    isFetching,
    error,
  };
}
