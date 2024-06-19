import { Card, CardContent, CardHeader, CardTitle } from "@/components/ui/card";
import { useWeather } from "@/data/weather";
import { City } from "@/models/city";
import { CloudIcon } from "lucide-react";
import { useTranslation } from "react-i18next";

export function CurrentWeather({ city }: { city: City }) {
    const { t } = useTranslation()
    const { weather } = useWeather(city.name)

    return (
        <Card>
            <CardHeader>
                <CardTitle>{t('current_weather.title')}</CardTitle>
            </CardHeader>
            <CardContent className="flex items-center justify-center gap-4">
                <div className="grid md:flex w-full items-center justify-center h-full gap-4 md:grid-cols-2 md:mt-10">
                    <div className="flex flex-col items-center gap-2">
                        <CloudIcon className="h-12 w-12 text-primary" />
                        <div>
                            <div className="text-4xl font-bold">{weather?.temperature}°C</div>
                            <div className="text-sm text-muted-foreground">{weather?.weather[0].description}</div>
                        </div>
                    </div>
                    <div className="text-sm text-muted-foreground text-center">
                        <div>{t('current_weather.feels')} {weather?.temperature_feels}°C</div>
                        <div>{t('current_weather.wind')} {weather?.wind_speed} km/h</div>
                        <div>{t('current_weather.humidity')} {weather?.humidity_percentage}%</div>
                    </div>
                </div>
            </CardContent>
        </Card>
    )
}