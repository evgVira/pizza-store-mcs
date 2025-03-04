import axios, {AxiosError, InternalAxiosRequestConfig} from "axios";

const API_BASE_URL = "http://localhost:8080";
const API_VERSION_V1 = "/api/v1"

const api = axios.create({
    baseURL: API_BASE_URL + API_VERSION_V1,
});

api.interceptors.request.use((config: InternalAxiosRequestConfig) => {
    if (config.url?.startsWith("/api/v1/user")) {
        return config;
    }
    const token = localStorage.getItem("accessToken");
    if (token) {
        config.headers.Authorization = "Bearer ${token}";
    }
    return config;
})

api.interceptors.response.use((response) => response, async (error: AxiosError) => {
    if (error.response?.status === 401) {
        try {
            const refreshToken = localStorage.getItem("refreshToken");
            if (!refreshToken) throw new Error("No refresh token");

            const {data} = await axios.post<{
                accessToken: string;
                refreshToken: string
            }>(`${API_BASE_URL + API_VERSION_V1}/user/token/refresh/${refreshToken}`);
            localStorage.setItem("accessToken", data.accessToken);
            localStorage.setItem("refreshToken", data.refreshToken);

            if (error.config) {
                error.config.headers = error.config.headers || {};
                error.config.headers.Authorization = `Bearer ${data.accessToken}`
            }
            return axios.request(error.response);
        } catch (refreshError) {
            localStorage.removeItem("accessToken");
            localStorage.removeItem("refreshToken");
            return Promise.reject(refreshError);
        }
    }
});

export default api;
