"use client";
import React from "react";
import AuthProviderButton from "@/components/AuthProviderButton/auth-provider-button";
import LoginForm from "@/feature/auth/LoginForm/login-form";
import { useAuth } from "@/feature/auth/hooks/useAuth";
import Warning from "@/components/Warning/warning";

type Status = "error" | "success" | "idle" | "pending";

const getWarningType = (status: Status): "success" | "error" => {
    if (status === "success") return "success";
    if (status === "error") return "error";
    return "success";
};

const warningOpened = (status: Status) => {
    return status === "success" || status === "error";
};

const LoginPage = () => {
    const { login } = useAuth();

    const warningContent = (): string => {
        if (login.isSuccess) return "Logged in successfully";
        if (login.error && login.error.response) {
            return login.error.response.data.message ?? login.error.response.data.error;
        }

        return "Unknown error";
    };

    return (
        <div className="flex h-screen items-center justify-center">
            <main className="w-96">
                <Warning open={warningOpened(login.status)} className="mb-2" type={getWarningType(login.status)}>
                    {warningContent()}
                </Warning>

                <h1 className="text-center text-3xl font-semibold">Welcome Back</h1>
                <p className="text-center font-medium">Login into your account</p>

                <div className="my-6 flex justify-center">
                    <AuthProviderButton providerName="google" />
                </div>

                <div className="flex items-center">
                    <div className="h-px flex-grow bg-zinc-300"></div>
                    <span className="mx-2">Or continue with</span>
                    <div className="h-px flex-grow bg-zinc-300"></div>
                </div>

                <div className="mt-6 flex flex-col">
                    <LoginForm />
                </div>
            </main>
        </div>
    );
};

export default LoginPage;
