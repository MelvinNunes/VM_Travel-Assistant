import { Card, CardContent, CardHeader, CardTitle } from "@/components/ui/card";
import { useWeatherForecast } from "@/data/weather";
import { City } from "@/models/city";
import { CloudSunIcon } from "lucide-react";


export function WeatherForecast({ city }: { city: City }) {
    const { forecast } = useWeatherForecast(city.name)

    return (
        <Card>
            <CardHeader>
                <CardTitle>Weather Forecast</CardTitle>
            </CardHeader>
            <CardContent className="grid grid-cols-5 gap-4">
                {forecast?.map((weather, index) => (
                    <div key={index} className="flex flex-col items-center gap-2 text-center">
                        <div className="font-medium">{weather.temperature_date}</div>
                        <CloudSunIcon className="h-8 w-8 text-primary" />
                        <div>
                            {`${weather.temperature}\u00B0C`}
                        </div>
                    </div>
                ))}
            </CardContent>
        </Card>
    )
}