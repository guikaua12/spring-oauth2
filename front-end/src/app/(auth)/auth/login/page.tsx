import React from "react";
import AuthProviderButton from "@/components/AuthProviderButton/auth-provider-button";
import LoginForm from "@/feature/auth/LoginForm/login-form";

const LoginPage = () => {
    return (
        <div className="flex h-screen items-center justify-center">
            <main className="w-96">
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
