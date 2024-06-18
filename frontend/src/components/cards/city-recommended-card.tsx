import { City } from "@/models/city";
import { MapPin } from "lucide-react";

export function CityRecommendedCard({ city }: { city: City }) {
    return (
        <div className="flex w-full max-w-md gap-3 items-center  border-b py-4">
            <MapPin className="text-gray-500" />
            <div className="flex flex-col">
                <h1 className="text-gray-600 font-medium">{city.country}</h1>
                <p className="text-sm text-gray-500">{city.name}</p>
            </div>
        </div>
    )
}