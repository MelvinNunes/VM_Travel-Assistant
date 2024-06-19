import { PopulationData } from "@/models/population";
import { useQuery } from "@tanstack/react-query";
import { HttpClient } from "./client/http-client";
import { API_ENDPOINTS } from "./client/endpoints";

type PopulationRes = {
  data: PopulationData[];
  message: string;
};
export function usePopulation(countryCode: string) {
  const { isLoading, isFetching, error, data } = useQuery({
    queryKey: ["getPopulationDetails", countryCode],
    queryFn: () =>
      HttpClient.get<PopulationRes>(
        API_ENDPOINTS.POPULATION_DATA(countryCode),
        null
      ),
  });
  return {
    population: data?.data,
    isLoading,
    isFetching,
    error,
  };
}
