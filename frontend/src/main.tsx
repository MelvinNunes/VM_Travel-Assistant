import React from 'react'
import ReactDOM from 'react-dom/client'

import './index.css'
import './lang/i18n';

import {
  createBrowserRouter,
  RouterProvider,
} from "react-router-dom";
import { ThemeProvider } from './providers/ThemeProvider.tsx';
import { Layout } from './layouts/Main.tsx';
import HomeScreen from './pages/Home.tsx';
import DefaultErrorPage from './sections/Error.tsx';
import SearchScreen from './pages/Search.tsx';
import { QueryClient, QueryClientProvider } from "@tanstack/react-query";
import RegisterScreen from './pages/Register.tsx';
import LoginScreen from './pages/Login.tsx';
import CityScreen from './pages/City.tsx';

const router = createBrowserRouter([
  {
    path: "/",
    element: <Layout />,
    errorElement: <DefaultErrorPage />,
    loader: () => import('./sections/Loading.tsx'),
    children: [
      {
        index: true,
        element: <HomeScreen />
      },
      {
        path: "/search",
        element: <SearchScreen />
      },
      {
        path: "/register",
        element: <RegisterScreen />
      },
      {
        path: "/login",
        element: <LoginScreen />
      },
      {
        path: "/city/:name",
        element: <CityScreen />
      }
    ]
  },
]);

const queryClient = new QueryClient();

ReactDOM.createRoot(document.getElementById('root')!).render(
  <React.StrictMode>
    <QueryClientProvider client={queryClient}>
      <ThemeProvider defaultTheme="system" storageKey="vite-ui-theme">
        <RouterProvider router={router} />
      </ThemeProvider>
    </QueryClientProvider>
  </React.StrictMode>,
)
