export const API_ENDPOINTS = {
  LOGIN: "/auth/login",
  REGISTER: "/auth/register",
  CITIES: (name?: string) => (name ? `/cities?name=${name}` : "/cities"),
  CITY: (cityName?: string) => `/cities/${cityName}`,
  CURRENT_WEATHER: (cityName: string) => `/cities/${cityName}/weather/current`,
  WEATHER_FORECAST: (cityName: string) =>
    `/cities/${cityName}/weather/forecast`,
  COUNTRIES: (region: string | null) =>
    region ? `/countries?region=${region}` : "/countries",
  COUNTRY: (countryName?: string) => `/countries/${countryName}`,
  POPULATION_DATA: (countryCode: string) =>
    `/countries/${countryCode}/population`,
  GDP_DATA: (countryCode: string) => `/countries/${countryCode}/gdp`,
  CURRENCY: (countryName: string) => `/countries/${countryName}/exchange-rates`,
};
