"use client";
import { createContext, ReactNode } from "react";
import { useMutation, UseMutationResult, useQuery, useQueryClient, UseQueryResult } from "@tanstack/react-query";
import { ApiError } from "@/types/ApiError";
import { AxiosError } from "axios";
import { login, LoginParams, register, RegisterParams, userInfo } from "@/services/UserService";
import { User } from "@/types/User";
import Cookies from "js-cookie";
import { useRouter } from "next/navigation";

type AuthProviderType = {
    user: UseQueryResult<User, AxiosError<ApiError>>;
    login: UseMutationResult<void, AxiosError<ApiError>, LoginParams>;
    register: UseMutationResult<void, AxiosError<ApiError>, RegisterParams>;
    logout: () => void;
};

const AuthContext = createContext<AuthProviderType>({} as AuthProviderType);

const AuthProvider = ({ children }: { children: ReactNode }) => {
    const { push } = useRouter();
    const queryClient = useQueryClient();

    const userQuery = useQuery<User, AxiosError<ApiError>>({
        queryKey: ["user"],
        queryFn: userInfo,
        retry: false,
        refetchInterval: 60000,
    });

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

    const logout = async () => {
        Cookies.remove("token");
        await queryClient.invalidateQueries({ queryKey: ["user"] });
        push("/auth/login");
    };

    return (
        <AuthContext.Provider value={{ user: userQuery, login: loginMutation, register: registerMutation, logout }}>
            {children}
        </AuthContext.Provider>
    );
};

export { AuthContext, AuthProvider };
