import { Card, CardContent, CardHeader, CardTitle } from "@/components/ui/card";
import { useCountry } from "@/data/country";
import { City } from "@/models/city";
import { Country } from "@/models/country";
import { FlagIcon, GlobeIcon } from "lucide-react";

export function CountryDetails({ city }: { city: City }) {
    const { country, isFetching } = useCountry(city.country)
    if (isFetching) {
        return (
            <div>

            </div>
        )
    }
    return CountryData({ country: country })
}

function CountryData({ country }: { country?: Country }) {
    return (
        <Card>
            <CardHeader>
                <CardTitle>Country</CardTitle>
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