"use client";
import React from "react";
import AuthProviderButton from "@/components/AuthProviderButton/auth-provider-button";
import RegisterForm from "@/feature/auth/RegisterForm/register-form";
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

const RegisterPage = () => {
    const { register } = useAuth();

    const warningContent = (): string => {
        if (register.isSuccess) return "Registered succesfully";
        if (register.error && register.error.response) {
            return register.error.response.data.message ?? register.error.response.data.error;
        }

        return "Unknown error";
    };

    return (
        <div className="flex h-screen items-center justify-center">
            <main className="w-96">
                <Warning open={warningOpened(register.status)} className="mb-2" type={getWarningType(register.status)}>
                    {warningContent()}
                </Warning>
                <h1 className="text-center text-3xl font-semibold">Create your account</h1>
                <p className="text-center font-medium">Sign up into your account</p>

                <div className="my-6 flex justify-center">
                    <AuthProviderButton providerName="google" />
                </div>

                <div className="flex items-center">
                    <div className="h-px flex-grow bg-zinc-300"></div>
                    <span className="mx-2">Or continue with</span>
                    <div className="h-px flex-grow bg-zinc-300"></div>
                </div>

                <div className="mt-6 flex flex-col">
                    <RegisterForm />
                </div>
            </main>
        </div>
    );
};

export default RegisterPage;
