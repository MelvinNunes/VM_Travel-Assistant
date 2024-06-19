import { City } from "@/models/city";
import { MapPin } from "lucide-react";
import { Skeleton } from "../ui/skeleton";
import { useNavigate } from "react-router-dom";

export function CityRecommendedCard({ city }: { city: City }) {
    const navigate = useNavigate()

    return (
        <div onClick={() => navigate(`/city/${city.name}`)} className="flex w-full max-w-md gap-3 items-center  border-b py-4 hover:bg-slate-50 dark:hover:bg-slate-700 rounded p-4 cursor-pointer">
            <MapPin className="text-gray-500 dark:text-slate-400" />
            <div className="flex flex-col">
                <h1 className="text-gray-600 dark:text-slate-400 font-medium">{city.name}</h1>
                <p className="text-sm text-gray-500 dark:text-slate-500">{city.country}</p>
            </div>
        </div>
    )
}

export function CityRecommendedCardSkeleton() {
    return (
        <div className="flex w-full max-w-md gap-3 items-center  border-b py-4">
            <MapPin className="text-gray-500 animate-pulse" />
            <div className="flex flex-col gap-2">
                <Skeleton className="h-4 w-[250px]" />
                <Skeleton className="h-2 w-[250px]" />
            </div>
        </div>
    )
}