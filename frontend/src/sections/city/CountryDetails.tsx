import { Card, CardContent, CardHeader, CardTitle } from "@/components/ui/card";
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
            <div>

            </div>
        )
    }

    return CountryData({ country: country, title: t('country.title') })
}

function CountryData({ country, title }: { country?: Country, title: string }) {
    return (
        <Card>
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
                <img className="rounded" src={country?.flag} alt="flag" />
            </CardContent>
        </Card>
    )
}