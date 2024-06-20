import CitySampleCard from "@/components/cards/city-sample-card";
import {
    Carousel,
    CarouselContent,
    CarouselItem,
    CarouselNext,
    CarouselPrevious,
} from "@/components/ui/carousel"
import { CitySample } from "@/models/city";

export function CitiesCarousel({ cities }: { cities: CitySample[] }) {
    return (
        <Carousel
            opts={{
                align: "center",
            }}
            className="w-full max-w-sm md:max-w-3xl lg:max-w-4xl"
        >
            <CarouselContent>
                {cities.map((city) => (
                    <CarouselItem key={city.id} className="md:basis-1/2 lg:basis-1/3">
                        <CitySampleCard city={city} />
                    </CarouselItem>
                ))}
            </CarouselContent>
            <CarouselPrevious className="dark:bg-slate-500" />
            <CarouselNext className="dark:bg-slate-500" />
        </Carousel>
    )
}