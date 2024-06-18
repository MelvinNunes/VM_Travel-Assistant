import { Route } from "@/routes/routes";


export function NavbarItems(routes: { routes: Route[] }) {
    return (
        <div>
            {routes.routes.map((route) => (
                <a
                    href={route.path}
                    className="text-muted-foreground hover:text-foreground"
                >
                    {route.name}
                </a>
            ))}
        </div>
    )
}