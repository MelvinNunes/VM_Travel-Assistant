export type ExchangeRates = {
  success: boolean;
  timestamp: number;
  base: string;
  date: string;
  rates: Map<string, number>;
};
