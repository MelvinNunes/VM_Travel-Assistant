import { Card, CardContent, CardHeader, CardTitle } from "@/components/ui/card";
import { useWeatherForecast } from "@/data/weather";
import { DateFormatter } from "@/utils/date-formatter";
import { useTranslation } from "react-i18next";
import Clouds from "@/assets/clouds.svg"
import Cloud from "@/assets/cloud.svg"
import Sun from "@/assets/sun.svg"

export function WeatherForecast({ cityName }: { cityName: string }) {
    const { t } = useTranslation()
    const { forecast } = useWeatherForecast(cityName)

    return (
        <Card>
            <CardHeader>
                <CardTitle>{t('forecast.title')}</CardTitle>
            </CardHeader>
            <CardContent className="grid md:grid-cols-2 lg:grid-cols-4 xl:grid-cols-5 gap-5">
                {forecast?.map((weather, index) => (
                    <div key={index} className="flex flex-col items-center gap-2 text-center border rounded p-3 border-gray-100 dark:text-slate-400 dark:border-slate-900">
                        {weather.temperature_date && <div className="font-medium">{DateFormatter.formatDate(weather.temperature_date)}</div>}
                        {weather?.temperature && <img className="h-8" src={weather.temperature > 30 ? Sun : weather.temperature > 20 ? Cloud : Clouds} alt="temperature" />}
                        <div>
                            {`${weather.temperature}\u00B0C`}
                        </div>
                    </div>
                ))}
            </CardContent>
        </Card>
    )
}


