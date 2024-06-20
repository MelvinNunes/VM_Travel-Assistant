import GoogleMapReact from 'google-map-react';

export function CityMapLocation({ lat, lon }: { lat: number, lon: number }) {
    return (
        <div style={{ height: '100vh', width: '100%' }}>
            <GoogleMapReact
                bootstrapURLKeys={{ key: import.meta.env.VITE_GOOGLE_MAPS_KEY }}
                defaultCenter={{
                    lat: lat,
                    lng: lon
                }}
                defaultZoom={10}
            >
            </GoogleMapReact>
        </div>

    )
}