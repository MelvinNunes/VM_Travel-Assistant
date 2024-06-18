import { Logo } from "@/components/logo"
import { useTranslation } from "react-i18next"
import facebookLogo from "@/assets/social/facebook.svg"
import twitterLogo from "@/assets/social/twitter.svg"
import instagramLogo from "@/assets/social/instagram.svg"
import linkedinLogo from "@/assets/social/linkedin.svg"
import { Separator } from "@/components/ui/separator"

const socialMedia = [
    {
        name: "Facebook",
        url: "https://www.facebook.com",
        icon: facebookLogo
    },
    {
        name: "Twitter",
        url: "https://www.twitter.com",
        icon: twitterLogo
    },
    {
        name: "Instagram",
        url: "https://www.instagram.com",
        icon: instagramLogo
    },
    {
        name: "Linkedin",
        url: "https://www.linkedin.com",
        icon: linkedinLogo
    }
]

export default function Footer() {
    const { t } = useTranslation()

    return (
        <div className="w-full h-full bg-slate-500 px-5 md:px-20 lg:px-40 mt-10">
            <div className="pt-10">
                <Logo title={t('title')} isFooter />
                <p className="text-white mt-2">{t('footer.description')}</p>
            </div>
            <div className="my-5">
                <h1 className="font-medium text-white mb-2">{t('footer.find_us')}</h1>
                <SocialMedia />
            </div>
            <Separator />
            <div className="my-5">
                <a href="/">
                    <p className="text-white text-sm">{t('footer.privacy')}</p>
                </a>
                <a href="/">
                    <p className="text-white text-sm">{t('footer.usage')}</p>
                </a>
                <a href="/">
                    <p className="text-white text-sm">{t('footer.cookies')}</p>
                </a>
            </div>
            <Separator />
            <div className="my-5">
                <p className="text-white text-sm ">{t('footer.copyright')}</p>
            </div>
        </div>
    )
}

function SocialMedia() {
    return (
        <div className="flex gap-2">
            {socialMedia.map((social) => (
                <a
                    href={social.url}
                    className="text-muted-foreground hover:text-foreground"
                >
                    <img className="text-gray-500" src={social.icon} alt={social.name} />
                </a>
            ))}
        </div>
    )
}

