import React from "react";
import AuthProviderButton from "@/components/AuthProviderButton/auth-provider-button";
import Input from "@/components/Input/input";
import Button from "@/components/Button/button";
import Link from "next/link";

const RegisterPage = () => {
    return (
        <div className="flex h-screen items-center justify-center">
            <main className="w-96">
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
                    <div className="flex flex-col">
                        <Input placeholder="Name" type="text" className="mb-2" />
                        <Input placeholder="Email" type="email" className="mb-2" />
                        <Input placeholder="Password" type="password" className="mb-0.5" />

                        <span className="mb-2 text-sm">
                            <span>Have an account? </span>
                            <Link className="font-semibold text-teal-500" href="/auth/login">
                                Sign in!
                            </Link>
                        </span>

                        <Button>LOG IN</Button>
                    </div>
                </div>
            </main>
        </div>
    );
};

export default RegisterPage;
