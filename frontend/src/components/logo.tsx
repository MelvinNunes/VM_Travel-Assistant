export function Logo({ title, isFooter }: { title: string, isFooter: boolean }) {
    return (

        <a href="/" className="flex gap-3 items-center mr-10">
            <img src={`${isFooter ? '/logo_white.svg' : '/logo.svg'}`} alt="app logo" className={`${isFooter && "text-white"}`} />
            <h1 className={`uppercase font-semibold  ${isFooter ? 'text-white' : "text-gray-500"}`}>{title}</h1>
        </a>
    )
}