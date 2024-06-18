import { CitySample } from "@/models/city";
// asia
import Kyoto from "@/assets/cities/kyoto.jpeg";
import HoiAn from "@/assets/cities/hoi_an.jpeg";
import Seoul from "@/assets/cities/seoul.jpeg";
import Tokyo from "@/assets/cities/tokyo.jpeg";
import Bangkok from "@/assets/cities/bangkok.jpeg";
// africa
import CapeTown from "@/assets/cities/cape_town.jpeg";
import Nairobi from "@/assets/cities/nairobi.jpeg";
import Marrakech from "@/assets/cities/marrakech.jpeg";
import Luxor from "@/assets/cities/luxor.jpeg";
import Windhoek from "@/assets/cities/windhoek.jpeg";
// europe
import Rome from "@/assets/cities/rome.jpeg";
import Paris from "@/assets/cities/paris.jpeg";
import Prague from "@/assets/cities/prague.jpeg";
import Athens from "@/assets/cities/athens.jpeg";
import Amsterdam from "@/assets/cities/amsterdam.jpeg";

export const asianCities: CitySample[] = [
  {
    id: 1,
    name: "Kyoto",
    country: "Japan",
    cityImage: Kyoto,
    population: 1448964,
  },
  {
    id: 2,
    name: "Hoi An",
    country: "Vietnam",
    cityImage: HoiAn,
    population: 121716,
  },
  {
    id: 3,
    name: "Seoul",
    country: "South Korea",
    cityImage: Seoul,
    population: 202232,
  },
  {
    id: 4,
    name: "Tokyo",
    country: "Japan",
    cityImage: Tokyo,
    population: 9668465,
  },
  {
    id: 5,
    name: "Bangkok",
    country: "Thailand",
    cityImage: Bangkok,
    population: 5676648,
  },
];

export const africanCities: CitySample[] = [
  {
    id: 1,
    name: "Cape Town",
    country: "South Africa",
    cityImage: CapeTown,
    population: 3776313,
  },
  {
    id: 2,
    name: "Marrakech",
    country: "Morocco",
    cityImage: Marrakech,
    population: 166711,
  },
  {
    id: 3,
    name: "Luxor",
    country: "Egypt",
    cityImage: Luxor,
    population: 202232,
  },
  {
    id: 4,
    name: "Nairobi",
    country: "Kenya",
    cityImage: Nairobi,
    population: 100000,
  },
  {
    id: 5,
    name: "Windhoek",
    country: "Namibia",
    cityImage: Windhoek,
    population: 431000,
  },
];

export const europeanCities: CitySample[] = [
  {
    id: 1,
    name: "Rome",
    country: "Italy",
    cityImage: Rome,
    population: 3776313,
  },
  {
    id: 2,
    name: "Paris",
    country: "France",
    cityImage: Paris,
    population: 166711,
  },
  {
    id: 3,
    name: "Athens",
    country: "Greece",
    cityImage: Athens,
    population: 202232,
  },
  {
    id: 4,
    name: "Prague",
    country: "Czech Republic",
    cityImage: Prague,
    population: 100000,
  },
  {
    id: 5,
    name: "Amsterdam",
    country: "Netherlands",
    cityImage: Amsterdam,
    population: 431000,
  },
];
