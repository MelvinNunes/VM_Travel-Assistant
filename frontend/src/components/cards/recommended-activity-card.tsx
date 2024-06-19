import { Card, CardContent } from "@/components/ui/card"

export default function RecommendedActivityCard({ title, description, backgroundImagePath }: { title: string, description: string, backgroundImagePath: string }) {
    return (
        <Card className="relative overflow-hidden rounded-lg w-56 md:w-80 h-56 md:h-80">
            <img
                src={backgroundImagePath}
                alt="Card background"
                className="absolute inset-0 h-full w-full object-cover"
            />
            <CardContent className="relative z-10 p-6 items-end text-white">
                <div className="absolute -bottom-36 md:-bottom-60 left-5">
                    <h3 className="font-bold">{title}</h3>
                    <p className="text-sm">{description}</p>
                </div>
            </CardContent>
        </Card>
    )
}