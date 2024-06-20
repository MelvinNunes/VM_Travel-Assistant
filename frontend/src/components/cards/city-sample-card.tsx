
import { Card, CardContent } from "@/components/ui/card"
import { Button } from "@/components/ui/button"
import { CitySample } from "@/models/city"
import { Users } from "lucide-react"
import { useTranslation } from "react-i18next"
import Formatter from "@/utils/formatter"
import { useNavigate } from "react-router-dom"

export default function CitySampleCard({ city }: { city: CitySample }) {
    const { t } = useTranslation()
    const navigate = useNavigate()

    return (
        <Card className="w-full rounded-lg my-3 overflow-hidden dark:bg-slate-900" >
            <img src={city.cityImage} alt="City Image" className="w-full h-48 object-cover" />
            <CardContent className="p-6 space-y-4">
                <div className="flex items-center justify-between">
                    <h3 className="text-2xl font-bold">{city.name}</h3>
                    <div className="flex gap-2 items-center bg-gray-100 px-3 py-1 rounded-full text-sm font-medium dark:bg-gray-800">
                        <Users className="w-5 h-5" />
                        {Formatter.formatPopulation(city.population)}
                    </div>
                </div>
                <div className="flex items-center gap-2">
                    <LocateIcon />
                    <span className="text-gray-500 dark:text-gray-400">{city.country}</span>
                </div>
                <Button onClick={() => navigate(`city/${city.name}`)} className="w-full">
                    {t('city_sample_card.details_button_label')}
                </Button>
            </CardContent>
        </Card>
    )
}

function LocateIcon() {
    return (
        <svg
            className="w-5 h-5 text-gray-500 dark:text-gray-400"
            xmlns="http://www.w3.org/2000/svg"
            width="24"
            height="24"
            viewBox="0 0 24 24"
            fill="none"
            stroke="currentColor"
            strokeWidth="2"
            strokeLinecap="round"
            strokeLinejoin="round"
        >
            <line x1="2" x2="5" y1="12" y2="12" />
            <line x1="19" x2="22" y1="12" y2="12" />
            <line x1="12" x2="12" y1="2" y2="5" />
            <line x1="12" x2="12" y1="19" y2="22" />
            <circle cx="12" cy="12" r="7" />
        </svg>
    )
}