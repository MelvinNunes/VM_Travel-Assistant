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
import { Outlet } from "react-router-dom"
import { NavbarItems } from "@/sections/NavbarItems"
import { Routes } from "@/routes/routes"
import { ModeToggle } from "@/components/ui/theme-toggler"
import LanguageSelector from "@/components/language-selector"
import { useTranslation } from "react-i18next"
import { Logo } from "@/components/logo"
import Footer from "@/sections/Footer"


export function Layout() {
    const { t } = useTranslation()

    return (
        <div className="flex min-h-screen w-full flex-col">
            <header className="sticky top-0 flex h-16 items-center gap-4 border-b bg-background px-4 md:px-6">
                <nav className="flex-col gap-6 text-md font-medium md:flex md:flex-row md:items-center md:gap-5 md:text-lg lg:gap-6">
                    <Logo title={t('title')} isFooter={false} />
                    <NavbarItems routes={Routes} />
                </nav>
                <div className="flex w-full items-center justify-end gap-4 md:ml-auto md:gap-2 lg:gap-4">
                    <LanguageSelector />
                    <ModeToggle />
                    <DropdownMenu>
                        <DropdownMenuTrigger asChild>
                            <Button variant="secondary" size="icon" className="rounded-full">
                                <CircleUser className="h-5 w-5" />
                                <span className="sr-only">Toggle user menu</span>
                            </Button>
                        </DropdownMenuTrigger>
                        <DropdownMenuContent align="end">
                            <DropdownMenuLabel>Melvin Nunes</DropdownMenuLabel>
                            <DropdownMenuSeparator />
                            <DropdownMenuItem>{t('login')}</DropdownMenuItem>
                            <DropdownMenuSeparator />
                            <DropdownMenuItem>{t('logout')}</DropdownMenuItem>
                        </DropdownMenuContent>
                    </DropdownMenu>
                </div>
            </header>
            <main className="flex flex-1 flex-col gap-4 md:gap-8">
                <Outlet />
            </main>
            <Footer />
        </div>
    )
}
