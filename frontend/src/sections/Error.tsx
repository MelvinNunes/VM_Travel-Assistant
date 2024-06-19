import { useTranslation } from "react-i18next"

export default function DefaultErrorPage() {
    const { t } = useTranslation()

    return (
        <div className="flex flex-col items-center justify-center min-h-[100dvh] bg-background text-foreground">
            <div className="container max-w-md px-4 py-12 space-y-6 text-center">
                <div className="space-y-2">
                    <h1 className="text-4xl font-bold tracking-tight sm:text-5xl">{t('error.title')}</h1>
                    <p className="text-muted-foreground">
                        {t('error.description')}
                    </p>
                </div>
                <a
                    href="/"
                    className="inline-flex h-10 items-center justify-center rounded-md bg-primary px-6 text-sm font-medium text-primary-foreground shadow transition-colors hover:bg-primary/90 focus-visible:outline-none focus-visible:ring-1 focus-visible:ring-ring disabled:pointer-events-none disabled:opacity-50"
                >
                    {t('error.home')}
                </a>
            </div>
        </div>
    )
}