import { Button } from "@/components/ui/button";
import { useTranslation } from "react-i18next";
import { useNavigate } from "react-router-dom";

export function GetStarted() {
    const { t } = useTranslation()
    const navigate = useNavigate()

    return (
        <div className="flex flex-col w-full justify-center items-center h-52 bg-cover bg-hero">
            <div className="text-center mb-10">
                <h1 className="font-medium text-white text-2xl">{t("home.get_started.title")}</h1>
                <p className="text-white">{t("home.get_started.description")}</p>
            </div>
            <Button onClick={() => navigate("login")} className="w-80">{t("home.get_started.button_label")}</Button>
        </div>
    )
}