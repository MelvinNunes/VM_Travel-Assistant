import RecommendedActivityCard from "@/components/cards/recommended-activity-card";
import { CitiesCarousel } from "@/components/cities-carousel";
import { SectionTitle } from "@/components/section-title";
import { africanCities, asianCities, europeanCities } from "@/data/static/cities";
import { GetStarted } from "@/sections/GetStartedBanner";
import { Hero } from "@/sections/Hero";
import { useTranslation } from "react-i18next";
import HomeIcon from "@/assets/home-check.svg"
import HeartIcon from "@/assets/heart-check.svg"
import { useAtom } from "jotai";
import { isUserAuthenticated } from "@/atoms/auth";

export default function HomeScreen() {
    const { t } = useTranslation()
    const [isUserAuth] = useAtom(isUserAuthenticated)

    return (
        <main className="flex flex-col w-full">
            <Hero />
            <div className="bg-slate-50 mb-4">
                <div className="bg-slate-200 flex gap-3 w-full items-center md:justify-center h-20 border border-slate-300 dark:border-slate-900 dark:bg-slate-700 px-4">
                    <img src={HomeIcon} alt="home-icon" className="w-6 h-6" />
                    <p className="text-slate-600 dark:text-slate-400 font-medium">{t('home.promo.one.title')}</p>
                </div>
                <div className="bg-slate-200 flex gap-3 w-full items-center md:justify-center h-20 border  border-slate-300 dark:border-slate-900 dark:bg-slate-700  px-4 ">
                    <img src={HeartIcon} alt="home-icon" className="w-6 h-6 " />
                    <p className="text-slate-600 dark:text-slate-400 font-medium">{t('home.promo.two.title')}</p>
                </div>
            </div>
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
            {!isUserAuth && <GetStarted />}
            <div className="w-full flex flex-col px-5">
                <div>
                    {/* Asia Bests */}
                    <SectionTitle title={t('home.section.best_asia.title')} description={t('home.section.best_asia.description')} />
                    <div className="flex w-full justify-center">
                        <CitiesCarousel cities={asianCities} />
                    </div>
                </div>
            </div>
        </main>
    )
}


