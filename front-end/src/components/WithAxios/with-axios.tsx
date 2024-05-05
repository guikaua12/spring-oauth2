"use client";
import { ReactNode, useEffect } from "react";
import { api } from "@/services/api";
import Cookies from "js-cookie";

const WithAxios = ({ children }: { children: ReactNode }) => {
    useEffect(() => {
        const id = api.interceptors.response.use(
            (response) => response,
            (error) => {
                console.log(error);

                const status = error.response.status;
                const token = Cookies.get("token");

                // unauthorized
                if (token && status === 401) {
                    console.log("Invalid token, logout.");
                    Cookies.remove("token");
                }

                return Promise.reject(error);
            }
        );

        return () => api.interceptors.response.eject(id);
    }, []);

    return children;
};

export default WithAxios;
