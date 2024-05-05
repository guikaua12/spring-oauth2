"use client";
import { createContext, ReactNode } from "react";
import { useMutation, UseMutationResult } from "@tanstack/react-query";
import { ApiError } from "@/types/ApiError";
import { AxiosError } from "axios";
import { login, LoginParams, register, RegisterParams } from "@/services/UserService";

type AuthProviderType = {
    login: UseMutationResult<void, AxiosError<ApiError>, LoginParams>;
    register: UseMutationResult<void, AxiosError<ApiError>, RegisterParams>;
};

const AuthContext = createContext<AuthProviderType>({} as AuthProviderType);

const AuthProvider = ({ children }: { children: ReactNode }) => {
    const loginMutation = useMutation<void, AxiosError<ApiError>, LoginParams>({
        mutationKey: ["login"],
        mutationFn: login,
        retry: false,
    });

    const registerMutation = useMutation<void, AxiosError<ApiError>, RegisterParams>({
        mutationKey: ["register"],
        mutationFn: register,
        retry: false,
    });

    return (
        <AuthContext.Provider value={{ login: loginMutation, register: registerMutation }}>
            {children}
        </AuthContext.Provider>
    );
};

export { AuthContext, AuthProvider };
