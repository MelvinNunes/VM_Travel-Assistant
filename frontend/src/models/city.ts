export type City = {
  id: number;
  type: string;
  city: string | null;
  name: string;
  country: string | null;
  country_code: string | null;
  region: string;
  region_code: string;
  latitude: number;
  longitude: number;
  population: number;
  place_type: string;
};

export type CitySample = {
  id: number;
  name: string;
  country: string;
  cityImage: string;
  population: number;
};
