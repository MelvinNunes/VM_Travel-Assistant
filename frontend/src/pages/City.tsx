import { useState } from "react"
import { Card, CardHeader, CardTitle, CardContent } from "@/components/ui/card"
import { CurrencyIcon, DollarSignIcon, MapPinIcon, TrendingUpIcon, UsersIcon } from "lucide-react"
import { CurrentWeather } from "@/sections/city/CurrentWeather"
import { CountryDetails } from "@/sections/city/CountryDetails"
import { useCity } from "@/data/city"
import { useParams } from "react-router-dom"
import { WeatherForecast } from "@/sections/city/WeatherForecast"

export default function CityScreen() {
    const [isAuthenticated,] = useState(false)
    const { name } = useParams()

    const { city, isFetching } = useCity(name);

    console.log(isFetching)

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
                                {city && <CurrentWeather city={city} />}
                                {city && <CountryDetails city={city} />}
                            </div>
                            {city && <WeatherForecast city={city} />}
                        </div>
                        <div className="grid gap-4">
                            {isAuthenticated ? (
                                <>
                                    <Card>
                                        <CardHeader>
                                            <CardTitle>Population</CardTitle>
                                        </CardHeader>
                                        <CardContent className="grid gap-2">
                                            <div className="flex items-center gap-2">
                                                <UsersIcon className="h-6 w-6 text-primary" />
                                                <div>8,728,000</div>
                                            </div>
                                            <div className="flex items-center gap-2">
                                                <TrendingUpIcon className="h-6 w-6 text-primary" />
                                                <div>+1.2% YoY</div>
                                            </div>
                                        </CardContent>
                                    </Card>
                                    <Card>
                                        <CardHeader>
                                            <CardTitle>GDP</CardTitle>
                                        </CardHeader>
                                        <CardContent className="grid gap-2">
                                            <div className="flex items-center gap-2">
                                                <DollarSignIcon className="h-6 w-6 text-primary" />
                                                <div>$1.5 Trillion</div>
                                            </div>
                                            <div className="flex items-center gap-2">
                                                <TrendingUpIcon className="h-6 w-6 text-primary" />
                                                <div>+3.2% YoY</div>
                                            </div>
                                        </CardContent>
                                    </Card>
                                    <Card>
                                        <CardHeader>
                                            <CardTitle>Currency</CardTitle>
                                        </CardHeader>
                                        <CardContent className="grid gap-2">
                                            <div className="flex items-center gap-2">
                                                <DollarSignIcon className="h-6 w-6 text-primary" />
                                                <div>USD</div>
                                            </div>
                                            <div className="flex items-center gap-2">
                                                <CurrencyIcon className="h-6 w-6 text-primary" />
                                                <div>1 USD = 0.92 EUR</div>
                                            </div>
                                        </CardContent>
                                    </Card>
                                </>
                            ) : (
                                <Card className="relative">
                                    <CardHeader>
                                        <CardTitle>Population</CardTitle>
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
                                        <div className="text-muted-foreground">Login to view more details</div>
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