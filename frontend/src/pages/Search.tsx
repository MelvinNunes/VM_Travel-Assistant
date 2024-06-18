import { Input } from "@/components/ui/input";
import { Separator } from "@/components/ui/separator";
import { SearchIcon } from "lucide-react";
import { useTranslation } from "react-i18next";

export default function Search() {
    const { t } = useTranslation()

    return (
        <div className="flex flex-col items-center ">
            {/* Search Input */}
            <div className="flex justify-center w-full px-5">
                <div className="relative w-full max-w-2xl my-8">
                    <Input
                        type="text"
                        placeholder={t('hero.search.placeholder')}
                        className="w-full rounded-md border border-gray-300 px-4 py-2 pr-10 text-gray-900 focus:border-blue-500 focus:outline-none focus:ring-2 focus:ring-blue-500 dark:border-gray-600 dark:bg-gray-800 dark:text-gray-100 dark:focus:border-blue-500"
                    />
                    <div className="absolute inset-y-0 right-0 flex items-center pr-3 pointer-events-none">
                        <SearchIcon className="w-5 h-5 text-gray-400 dark:text-gray-500" />
                    </div>
                </div>
            </div>
            {/* End Input */}
            <Separator />
            <h1 className="text-gray-500 font-medium my-3">{t('search_page.recommendation_title')}</h1>
        </div>
    )
}