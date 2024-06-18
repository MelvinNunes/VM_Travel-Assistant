import RecommendedActivityCard from "@/components/cards/recommended-activity-card";
import { CitiesCarousel } from "@/components/cities-carousel";
import { SectionTitle } from "@/components/section-title";
import { africanCities, asianCities, europeanCities } from "@/data/static/cities";
import { GetStarted } from "@/sections/GetStartedBanner";
import { Hero } from "@/sections/Hero";
import { useTranslation } from "react-i18next";

export default function Home() {
    const { t } = useTranslation()

    return (
        <div className="flex flex-col w-full">
            <Hero />
            {/* Popular Static Destinations to improve apps experience */}
            <div className="w-full flex flex-col px-5">
                <div>
                    {/* Africa Bests */}
                    <SectionTitle title={t('home.section.best_africa.title')} description={t('home.section.best_africa.description')} />
                    <div className="flex w-full justify-center">
                        <CitiesCarousel cities={africanCities} />
                    </div>
                </div>
                <div>
                    {/* Europe Bests */}
                    <SectionTitle title={t('home.section.best_europe.title')} description={t('home.section.best_europe.description')} />
                    <div className="flex w-full justify-center">
                        <CitiesCarousel cities={europeanCities} />
                    </div>
                </div>
            </div>
            <div className="flex w-full justify-center gap-3 my-6">
                <RecommendedActivityCard title={t('home.recommendation_card.holidays.title')} description={t('home.recommendation_card.holidays.description')} backgroundImagePath="/holidays_placeholder.jpeg" />
                <RecommendedActivityCard title={t('home.recommendation_card.work.title')} description={t('home.recommendation_card.work.description')} backgroundImagePath="/remote_work_placeholder.jpeg" />
            </div>
            <GetStarted />
            <div className="w-full flex flex-col px-5">
                <div>
                    {/* Asia Bests */}
                    <SectionTitle title={t('home.section.best_asia.title')} description={t('home.section.best_asia.description')} />
                    <div className="flex w-full justify-center">
                        <CitiesCarousel cities={asianCities} />
                    </div>
                </div>
            </div>
        </div>
    )
}


