import { Card, CardHeader, CardTitle, CardContent } from "@/components/ui/card"
import { MapPinIcon, TrendingUpIcon, UsersIcon } from "lucide-react"
import { CurrentWeather } from "@/sections/city/CurrentWeather"
import { CountryDetails } from "@/sections/city/CountryDetails"
import { useCity } from "@/data/city"
import { useParams } from "react-router-dom"
import { WeatherForecast } from "@/sections/city/WeatherForecast"
import { useAtom } from "jotai"
import { isUserAuthenticated } from "@/atoms/auth"
import { PopulationDetails } from "@/sections/city/PopulationDetails"
import { GdpDetails } from "@/sections/city/GdpDetails"
import { CurrencyDetails } from "@/sections/city/CurrencyDetails"
import { useTranslation } from "react-i18next"

export default function CityScreen() {
    const [isAuthenticated,] = useAtom(isUserAuthenticated)
    const { t } = useTranslation()
    const { name } = useParams()

    const { city } = useCity(name);

    return (
        <div>
            <div className="flex items-center gap-2 px-4 md:px-12 mt-7 dark:pb-8">
                <MapPinIcon className="h-6 w-6 text-primary" />
                <h1 className="text-xl dark:text-slate-400 font-semibold">{city?.name}</h1>
            </div>
            <div className="grid min-h-screen w-full bg-background text-foreground">
                <main className="container grid gap-4 px-4 py-8 md:px-6 md:py-10">
                    <section className="grid gap-4 md:grid-cols-[1fr_300px] lg:grid-cols-[1fr_400px]">
                        <div className="grid gap-4">
                            <div className="grid grid-cols-2 gap-4">
                                {name && <CurrentWeather cityName={name} />}
                                {city && <CountryDetails city={city} />}
                            </div>
                            {name && <WeatherForecast cityName={name} />}
                        </div>
                        <div className="grid gap-4">
                            {isAuthenticated ? (
                                <>
                                    {city && <PopulationDetails countryCode={city.country_code} />}
                                    {city && <GdpDetails countryCode={city.country_code} />}
                                    {city && <CurrencyDetails countryName={city.country} />}
                                </>
                            ) : (
                                <Card className="relative">
                                    <CardHeader>
                                        <CardTitle>{t('population.title')}</CardTitle>
                                    </CardHeader>
                                    <CardContent className="grid gap-2">
                                        <div className="flex items-center gap-2 text-muted-foreground">
                                            <UsersIcon className="h-6 w-6" />
                                            <div className="blur-sm">8,728,000</div>
                                        </div>
                                        <div className="flex items-center gap-2 text-muted-foreground">
                                            <TrendingUpIcon className="h-6 w-6" />
                                            <div className="blur-sm">+1.2% YoY</div>
                                        </div>
                                    </CardContent>
                                    <div className="absolute inset-0 bg-background/80 backdrop-blur-sm flex items-center justify-center">
                                        <div className="text-muted-foreground">{t('login_to_view_more')}</div>
                                    </div>
                                </Card>
                            )}
                        </div>
                    </section>
                </main>
            </div>
        </div>
    )
}