import { Input } from "@/components/ui/input";
import { SearchIcon } from "lucide-react";
import { useTranslation } from "react-i18next";
import { useNavigate } from "react-router-dom";

export function Hero() {
    const { t } = useTranslation()
    const navigate = useNavigate()

    return (
        <div className="flex flex-col justify-center items-center bg-hero bg-cover w-full h-72 px-6">
            <div className="flex flex-col justify-center items-center gap-2 mb-5">
                <h1 className="text-3xl font-bold text-white">{t('hero.title')}</h1>
                <p className="text-white">{t('hero.description')}</p>
            </div>
            <div className="relative w-full max-w-lg mt-8 z-0">
                <div className="absolute inset-y-0 left-0 flex items-center pl-3 pointer-events-none">
                    <SearchIcon className="w-5 h-5 text-gray-400 dark:text-gray-500" />
                </div>
                <Input
                    type="text"
                    placeholder={t('hero.search.placeholder')}
                    onClick={() => navigate("search")}
                    className="w-full rounded-md border border-gray-300 px-4 py-2 pl-10 text-gray-900 focus:border-blue-500 focus:outline-none focus:ring-2 focus:ring-blue-500 dark:border-gray-600 dark:bg-gray-800 dark:text-gray-100 dark:focus:border-blue-500"
                />
            </div>
        </div>
    )
}