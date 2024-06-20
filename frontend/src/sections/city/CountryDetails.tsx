import { Card, CardContent, CardHeader, CardTitle } from "@/components/ui/card";
import { Skeleton } from "@/components/ui/skeleton";
import { useCountry } from "@/data/country";
import { City } from "@/models/city";
import { Country } from "@/models/country";
import { FlagIcon, GlobeIcon } from "lucide-react";
import { useTranslation } from "react-i18next";

export function CountryDetails({ city }: { city: City }) {
    const { t } = useTranslation()

    const { country, isFetching } = useCountry(city.country)
    if (isFetching) {
        return (
            <CountryDataSkeleton />
        )
    }

    return CountryData({ country: country, title: t('country.title') })
}

function CountryData({ country, title }: { country?: Country, title: string }) {
    return (
        <Card className="dark:bg-slate-900">
            <CardHeader>
                <CardTitle>{title}</CardTitle>
            </CardHeader>
            <CardContent className="grid gap-2">
                <div className="grid gap-2">
                    <div className="flex items-center gap-2">
                        <FlagIcon className="h-6 w-6 text-primary" />
                        <div>{country?.name}</div>
                    </div>
                    <div className="flex items-center gap-2">
                        <GlobeIcon className="h-6 w-6 text-primary" />
                        <div>{country?.region}</div>
                    </div>
                </div>
                <img className="rounded w-40 my-2" src={country?.flag} alt="flag" />
            </CardContent>
        </Card>
    )
}

function CountryDataSkeleton() {
    return (
        <Card className="dark:bg-slate-900">
            <CardHeader>
                <CardTitle><Skeleton className="h-5 w-60"></Skeleton></CardTitle>
            </CardHeader>
            <CardContent className="grid gap-2">
                <div className="grid gap-2">
                    <div className="flex items-center gap-2">
                        <FlagIcon className="h-6 w-6 text-primary" />
                        <div><Skeleton className="h-5 w-60"></Skeleton></div>
                    </div>
                    <div className="flex items-center gap-2">
                        <GlobeIcon className="h-6 w-6 text-primary" />
                        <div><Skeleton className="h-5 w-60"></Skeleton></div>
                    </div>
                </div>
                <Skeleton className="h-40 w-40"></Skeleton>
            </CardContent>
        </Card>
    )
}
