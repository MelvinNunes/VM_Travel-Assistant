import { Card, CardContent, CardHeader, CardTitle } from "@/components/ui/card";
import { usePopulation } from "@/data/population";
import Formatter from "@/utils/formatter";
import { TrendingUpIcon, UsersIcon } from "lucide-react";
import { useTranslation } from "react-i18next";

export function PopulationDetails({ countryCode }: { countryCode: string }) {
    const { t } = useTranslation()
    const { population } = usePopulation(countryCode)
    return (
        <Card>
            <CardHeader>
                <CardTitle>{t('population.title')}</CardTitle>
            </CardHeader>
            <CardContent className="grid gap-4">
                {population?.slice(0, 25).map((population) => (
                    <div className="grid grid-cols-2 gap-2">
                        <div className="flex items-center gap-2">
                            <UsersIcon className="h-6 w-6 text-primary" />
                            {population.value ? <div>{Formatter.formatPopulation(population.value)}</div> : <div>{t('no_data')}</div>}

                        </div>
                        <div className="flex items-center gap-2">
                            <TrendingUpIcon className="h-6 w-6 text-primary" />
                            <div>{population.date}</div>
                        </div>
                    </div>
                ))}
            </CardContent>
        </Card>
    )
}