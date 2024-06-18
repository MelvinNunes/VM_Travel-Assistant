import { SectionTitle } from "@/components/section-title";
import { Hero } from "@/sections/Hero";
import { useTranslation } from "react-i18next";

export default function Home() {
    const { t } = useTranslation()

    return (
        <div>
            <Hero />
            <div className="px-5">
                <SectionTitle title={t('home.section.best_africa.title')} description={t('home.section.best_africa.description')} />
                <SectionTitle title={t('home.section.best_europe.title')} description={t('home.section.best_europe.description')} />
            </div>
        </div>
    )
}


