import { Card, CardContent, CardHeader, CardTitle } from "@/components/ui/card";
import { useWeather } from "@/data/weather";
import { useTranslation } from "react-i18next";
import Clouds from "@/assets/clouds.svg"
import Cloud from "@/assets/cloud.svg"
import Sun from "@/assets/sun.svg"
import { Skeleton } from "@/components/ui/skeleton";

export function CurrentWeather({ cityName }: { cityName: string }) {
    const { t } = useTranslation()
    const { weather, isFetching } = useWeather(cityName)

    if (isFetching) {
        return <CurrentWeatherSkeleton />
    }

    return (
        <Card className="dark:bg-slate-900">
            <CardHeader>
                <CardTitle>{t('current_weather.title')}</CardTitle>
            </CardHeader>
            <CardContent className="flex items-center justify-center gap-4">
                <div className="grid md:flex w-full items-center justify-center h-full gap-4 md:grid-cols-2">
                    <div className="flex flex-col items-center gap-2">
                        {weather?.temperature && <img className="h-20 w-20" src={weather.temperature > 30 ? Sun : weather.temperature > 20 ? Cloud : Clouds} alt="temperature" />}
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


function CurrentWeatherSkeleton() {
    return (
        <Card className="dark:bg-slate-900">
            <CardHeader>
                <CardTitle>
                    <Skeleton className="h-8 w-full" ></Skeleton>
                </CardTitle>
            </CardHeader>
            <CardContent className="flex items-center justify-center gap-4">
                <div className="grid md:flex w-full items-center justify-center h-full gap-4 md:grid-cols-2">
                    <div className="flex flex-col items-center gap-2">
                        <Skeleton className="h-20 w-20 lg:h-60 lg:w-60" />
                        <div>
                            <Skeleton className="h-4 w-full"></Skeleton>
                            <Skeleton className="h-4 w-full"></Skeleton>
                        </div>
                    </div>
                    <div className="text-sm text-muted-foreground text-center">
                        <Skeleton className="h-5 lg:w-40"></Skeleton>
                        <Skeleton className="h-5 lg:w-40"></Skeleton>
                        <Skeleton className="h-5 lg:w-40"></Skeleton>
                    </div>
                </div>
            </CardContent>
        </Card>
    )
}