type Indicator = {
  id: string;
  value: string;
};

type CountryInfo = {
  id: string;
  value: string;
};

export type GDPData = {
  indicator: Indicator;
  country: CountryInfo;
  countryiso3code: string;
  date: string;
  value: number | null;
  unit: string;
  decimal: number;
  obs_status: string;
};
