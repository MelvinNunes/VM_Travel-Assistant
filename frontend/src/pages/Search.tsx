import { CityRecommendedCard } from "@/components/cards/city-recommended-card";
import { Input } from "@/components/ui/input";
import { Separator } from "@/components/ui/separator";
import { City } from "@/models/city";
import { SearchIcon } from "lucide-react";
import { useTranslation } from "react-i18next";

export default function Search() {
    const { t } = useTranslation()

    const cities: City[] = [
        {
            "id": 659,
            "type": "CITY",
            "city": "Abu Dhabi",
            "name": "Abu Dhabi",
            "country": "United Arab Emirates",
            "country_code": "AE",
            "region": "Emirate of Abu Dhabi",
            "region_code": "AZ",
            "latitude": 24.451111111,
            "longitude": 54.396944444,
            "population": 1483000,
            "place_type": "CITY"
        },
        {
            "id": 638,
            "type": "CITY",
            "city": "Ajman",
            "name": "Ajman",
            "country": "United Arab Emirates",
            "country_code": "AE",
            "region": "Emirate of Ajman",
            "region_code": "AJ",
            "latitude": 25.399444444,
            "longitude": 55.479722222,
            "population": 490035,
            "place_type": "CITY"
        },
        {
            "id": 644,
            "type": "CITY",
            "city": "Al Ain",
            "name": "Al Ain",
            "country": "United Arab Emirates",
            "country_code": "AE",
            "region": "Emirate of Abu Dhabi",
            "region_code": "AZ",
            "latitude": 24.2075,
            "longitude": 55.744722222,
            "population": 766936,
            "place_type": "CITY"
        },
        {
            "id": 3704017,
            "type": "CITY",
            "city": "Dibba Al-Fujairah",
            "name": "Dibba Al-Fujairah",
            "country": "United Arab Emirates",
            "country_code": "AE",
            "region": "Emirate of Fujairah",
            "region_code": "FU",
            "latitude": 25.616667,
            "longitude": 56.266667,
            "population": 41017,
            "place_type": "CITY"
        },
        {
            "id": 3519123,
            "type": "CITY",
            "city": "Dubai",
            "name": "Dubai",
            "country": "United Arab Emirates",
            "country_code": "AE",
            "region": "Emirate of Dubai",
            "region_code": "DU",
            "latitude": 25.269722222,
            "longitude": 55.309444444,
            "population": 3564931,
            "place_type": "CITY"
        }
    ]

    return (
        <div className="flex flex-col items-center w-full">
            {/* Search Input */}
            <div className="flex justify-center w-full px-5">
                <div className="relative w-full max-w-2xl my-8">
                    <Input
                        type="text"
                        placeholder={t('hero.search.placeholder')}
                        className="w-full rounded-md border border-gray-300 px-4 py-2 pr-10 text-gray-900 focus:border-blue-500 focus:outline-none focus:ring-2 focus:ring-blue-500 dark:border-gray-600 dark:bg-gray-800 dark:text-gray-100 dark:focus:border-blue-500"
                    />
                    <div className="absolute inset-y-0 right-0 flex items-center pr-3 pointer-events-none">
                        <SearchIcon className="w-5 h-5 text-gray-400 dark:text-gray-500" />
                    </div>
                </div>
            </div>
            {/* End Input */}
            <Separator />
            <h1 className="text-gray-500 font-medium my-3">{t('search_page.recommendation_title')}</h1>
            <CitiesYouMightLike cities={cities} />
        </div>
    )
}

function CitiesYouMightLike({ cities }: { cities: City[] }) {
    return (
        <div className="flex flex-col w-full justify-center items-center gap-3  px-10">
            {cities.map((city) => (
                <CityRecommendedCard city={city} key={city.id} />
            ))}
        </div>
    )
}