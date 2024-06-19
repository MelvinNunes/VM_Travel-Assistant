import { Card, CardContent, CardHeader, CardTitle } from "@/components/ui/card";
import { useCountryCurrencies } from "@/data/currency";
import { DollarSignIcon } from "lucide-react";
import { useTranslation } from "react-i18next";

export function CurrencyDetails({ countryName }: { countryName: string }) {
    const { t } = useTranslation()
    const { currencies } = useCountryCurrencies(countryName)

    return (
        <Card>
            <CardHeader>
                <CardTitle>{t('currency.title')}</CardTitle>
            </CardHeader>
            <CardContent className="grid gap-2">
                <div className="flex items-center gap-2">
                    <DollarSignIcon className="h-6 w-6 text-primary" />
                    <div>{currencies?.base} {t('currency.base')}</div>
                </div>
                <div className="flex flex-col gap-2">
                    {currencies?.rates && Object.entries(currencies.rates).slice(0, 5).map(([rate, value]) => (
                        <div className="flex gap-1 items-center" key={rate}><DollarSignIcon className="h-3 w-3 text-primary" /> 1 {currencies.base} = {value} {rate}</div>
                    ))}
                </div>
            </CardContent>
        </Card>
    )
}