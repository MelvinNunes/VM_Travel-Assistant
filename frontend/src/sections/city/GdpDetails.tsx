import { Card, CardContent, CardHeader, CardTitle } from "@/components/ui/card";
import { useGdp } from "@/data/gdp";
import Formatter from "@/utils/formatter";
import { DollarSignIcon, TrendingUpIcon } from "lucide-react";
import { useTranslation } from "react-i18next";

export function GdpDetails({ countryCode }: { countryCode: string }) {
    const { t } = useTranslation()
    const { gdp } = useGdp(countryCode)

    return (
        <Card>
            <CardHeader>
                <CardTitle>{t('gdp.title')}</CardTitle>
            </CardHeader>
            <CardContent className="grid gap-2">
                {gdp?.slice(0, 8).map((gdpData) => (
                    <div className="grid grid-cols-2 gap-6">
                        <div className="flex items-center gap-2">
                            <DollarSignIcon className="h-6 w-6 text-primary" />
                            {gdpData.value ? <div>{Formatter.formatMoney(gdpData.value)}</div> : <div>{t('no_data')}</div>}
                        </div>
                        <div className="flex items-center gap-2">
                            <TrendingUpIcon className="h-6 w-6 text-primary" />
                            <div>{gdpData.date}</div>
                        </div>
                    </div>
                ))}
            </CardContent>
        </Card>
    )
}