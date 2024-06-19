import { CityRecommendedCard, CityRecommendedCardSkeleton } from "@/components/cards/city-recommended-card";
import { Input } from "@/components/ui/input";
import { Separator } from "@/components/ui/separator";
import { useCities } from "@/data/city";
import { City } from "@/models/city";
import { useQueryClient } from "@tanstack/react-query";
import { SearchIcon } from "lucide-react";
import { useEffect, useState } from "react";
import { useTranslation } from "react-i18next";

export default function SearchScreen() {
    const { t } = useTranslation()
    const queryClient = useQueryClient()
    const [query, setQuery] = useState<string>("")
    const { cities, isFetching } = useCities({ name: query })


    useEffect(() => {
        queryClient.invalidateQueries({ queryKey: ["getAllCities"] })
        // eslint-disable-next-line react-hooks/exhaustive-deps
    }, [query])

    return (
        <main className="flex flex-col items-center w-full">
            {/* Search Input */}
            <div className="flex justify-center w-full px-5">
                <div className="relative w-full max-w-2xl my-8">
                    <Input
                        type="text"
                        onChange={(e) => setQuery(e.target.value)}
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

            <h1 className="text-slate-400 font-medium my-3">{query ? t('search_page.results') : t('search_page.recommendation_title')}</h1>
            {isFetching ? <SkeletonForCities /> : <CitiesYouMightLike cities={cities} />}
        </main>
    )
}

function CitiesYouMightLike({ cities }: { cities: City[] }) {
    return (
        <div className="flex flex-col w-full justify-center items-center gap-3  px-10 mb-10">
            {cities.map((city) => (
                <CityRecommendedCard city={city} key={city.id} />
            ))}
        </div>
    )
}

function SkeletonForCities() {
    return Array.from({ length: 6 }).map((_, index) => (
        <CityRecommendedCardSkeleton key={index} />
    ))
}