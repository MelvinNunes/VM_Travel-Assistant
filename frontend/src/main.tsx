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
import Home from './pages/Home.tsx';
import DefaultErrorPage from './pages/Error.tsx';

const router = createBrowserRouter([
  {
    path: "/",
    element: <Layout />,
    errorElement: <DefaultErrorPage />,
    loader: () => import('./pages/Loading.tsx'),
    children: [
      {
        index: true,
        element: <Home />
      }
    ]
  },
]);

ReactDOM.createRoot(document.getElementById('root')!).render(
  <React.StrictMode>
    <ThemeProvider defaultTheme="system" storageKey="vite-ui-theme">
      <RouterProvider router={router} />
    </ThemeProvider>
  </React.StrictMode>,
)
