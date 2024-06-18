import axios, { AxiosInstance } from "axios";
import { getAuthToken } from "./token.utils";
import { ConfigValue } from "@/config";

const Axios: AxiosInstance = axios.create({
  baseURL: ConfigValue.API_BASE_URL,
  timeout: 150000000,
  headers: {
    "Content-Type": "application/json",
  },
});

Axios.interceptors.request.use(
  (config) => {
    const token = getAuthToken();
    if (token) {
      //@ts-expect-error error can happen here
      config.headers = {
        ...config.headers,
        Authorization: `Bearer ${token}`,
      };
    }
    return config;
  },
  (error) => {
    return Promise.reject(error);
  }
);

// Axios.interceptors.response.use(
//   (response) => response,
//   (error) => {
//     if (
//       (error.response && error.response.status === 401) ||
//       (error.response && error.response.status === 403) ||
//       (error.response &&
//         error.response.data.message === "PIXER_ERROR.NOT_AUTHORIZED")
//     ) {
//       removeAuthToken();
//     }
//     return Promise.reject(error);
//   }
// );

export class HttpClient {
  static async get<T>(url: string, params?: unknown) {
    const response = await Axios.get<T>(url, { params });
    return response.data;
  }

  static async post<T>(url: string, data: unknown) {
    const response = await Axios.post<T>(url, data);
    return response.data;
  }

  static async put<T>(url: string, data: unknown) {
    const response = await Axios.put<T>(url, data);
    return response.data;
  }

  static async delete<T>(url: string) {
    const response = await Axios.delete<T>(url);
    return response.data;
  }
}
