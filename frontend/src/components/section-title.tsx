export function SectionTitle({ title, description }: { title: string, description: string }) {
    return (
        <div className="flex flex-col my-3">
            <h1 className="text-md font-semibold text-slate-900">{title}</h1>
            <p className="text-sm text-gray-500">{description}</p>
        </div>
    )
}