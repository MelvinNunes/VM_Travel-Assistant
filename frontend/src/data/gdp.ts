import { useQuery } from "@tanstack/react-query";
import { HttpClient } from "./client/http-client";
import { API_ENDPOINTS } from "./client/endpoints";
import { GDPData } from "@/models/gdp";

type GdpRes = {
  data: GDPData[];
  message: string;
};

export function useGdp(countryCode: string) {
  const { isLoading, isFetching, error, data } = useQuery({
    queryKey: ["getGdpDetails", countryCode],
    queryFn: () =>
      HttpClient.get<GdpRes>(API_ENDPOINTS.GDP_DATA(countryCode), null),
  });
  return {
    gdp: data?.data,
    isLoading,
    isFetching,
    error,
  };
}
