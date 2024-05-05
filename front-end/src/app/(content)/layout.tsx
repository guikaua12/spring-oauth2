"use client";
import React from "react";
import Header from "@/components/Header/header";
import { useAuth } from "@/feature/auth/hooks/useAuth";
import { useRouter } from "next/navigation";
import LoadingFullscreen from "@/components/LoadingFullscreen/loading-fullscreen";

export default function RootLayout({
    children,
}: Readonly<{
    children: React.ReactNode;
}>) {
    const { user } = useAuth();
    const { push } = useRouter();

    if (user.isLoading) {
        return <LoadingFullscreen />;
    }

    if (user.error) {
        push("/auth/login");
    }

    return (
        <>
            <Header />
            {children}
        </>
    );
}
