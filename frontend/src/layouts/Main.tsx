import {
    CircleUser,
} from "lucide-react"

import { Button } from "@/components/ui/button"
import {
    DropdownMenu,
    DropdownMenuContent,
    DropdownMenuItem,
    DropdownMenuLabel,
    DropdownMenuSeparator,
    DropdownMenuTrigger,
} from "@/components/ui/dropdown-menu"
import { Outlet, useNavigate } from "react-router-dom"
import { NavbarItems } from "@/sections/NavbarItems"
import { Routes } from "@/routes/routes"
import { ModeToggle } from "@/components/ui/theme-toggler"
import LanguageSelector from "@/components/language-selector"
import { useTranslation } from "react-i18next"
import { Logo } from "@/components/logo"
import Footer from "@/sections/Footer"
import { Toaster } from "@/components/ui/toaster"
import { useAtom } from "jotai"
import { isUserAuthenticated } from "@/atoms/auth"
import { useGetUser } from "@/data/auth"
import { removeAuthToken } from "@/data/client/token.utils"
import { useEffect } from "react"
import { Skeleton } from "@/components/ui/skeleton"


export function Layout() {
    const { t } = useTranslation()
    const navigate = useNavigate()
    const [isLoggedIn, setIsLoggenIn] = useAtom(isUserAuthenticated)

    const { account, isFetching } = useGetUser()

    useEffect(() => {
        if (account && !isLoggedIn) {
            setIsLoggenIn(true)
        }
        // eslint-disable-next-line react-hooks/exhaustive-deps
    }, [isFetching])

    return (
        <div className="flex min-h-screen w-full flex-col">
            <header className="sticky top-0 flex z-50 h-16 items-center gap-4 border-b dark:border-none bg-background px-4 md:px-6">
                <nav className="flex-col gap-6 text-md font-medium md:flex md:flex-row md:items-center md:gap-5 md:text-lg lg:gap-6">
                    <Logo title={t('title')} isFooter={false} />
                    <NavbarItems routes={Routes} />
                </nav>
                <div className="flex w-full items-center justify-end gap-4 md:ml-auto md:gap-2 lg:gap-4">
                    <LanguageSelector />
                    <ModeToggle />

                    {isLoggedIn ? <DropdownMenu>
                        <DropdownMenuTrigger asChild>
                            <Button variant="secondary" size="icon" className="rounded-full">
                                <CircleUser className="h-5 w-5" />
                                <span className="sr-only">Toggle user menu</span>
                            </Button>
                        </DropdownMenuTrigger>
                        <DropdownMenuContent align="end">
                            <DropdownMenuLabel>{account?.name}</DropdownMenuLabel>
                            <DropdownMenuSeparator />
                            <DropdownMenuItem onClick={() => {
                                removeAuthToken()
                                setIsLoggenIn(false)
                            }}>{t('logout')}
                            </DropdownMenuItem>
                        </DropdownMenuContent>
                    </DropdownMenu> : isFetching ? <Skeleton className="h-6 w-10"></Skeleton> : <Button onClick={() => navigate("login")}>{t('login')}</Button>}

                </div>
            </header>
            <main className="flex flex-1 flex-col gap-4 md:gap-8 dark:bg-slate-800">
                <Outlet />
            </main>
            <Footer />
            <Toaster />
        </div>
    )
}
