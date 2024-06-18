export const API_ENDPOINTS = {
  CITIES: (name?: string) => (name ? `/cities?name=${name}` : "/cities"),
  CITY: (cityName: string) => `/cities/${cityName}`,
  CURRENT_WEATHER: (cityName: string) => `/${cityName}/weather/current`,
  WEATHER_FORECAST: (cityName: string) => `/${cityName}/weather/forecast`,
  COUNTRIES: (region: string | null) =>
    region ? `/countries?region=${region}` : "/countries",
  COUNTRY: (countryName: string) => `/countries/${countryName}`,
  POPULATION_DATA: (countryCode: string) =>
    `/countries/${countryCode}/population`,
  GDP_DATA: (countryCode: string) => `/countries/${countryCode}/gdp`,
  CURRENCY: (countryName: string) => `/countries/${countryName}/exchange-rates`,
};
