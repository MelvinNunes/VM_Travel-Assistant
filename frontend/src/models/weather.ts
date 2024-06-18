type Weather = {
  id: number;
  main: string;
  description: string;
  icon: string;
};

export type WeatherData = {
  weather: Weather[];
  temperature: number;
  temperature_min: number;
  temperature_max: number;
  temperature_feels: number;
  temperature_pressure: number;
  humidity_percentage: number;
  wind_speed: number;
  temperature_date: string | null;
};
