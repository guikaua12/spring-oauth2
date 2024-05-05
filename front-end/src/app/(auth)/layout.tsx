"use client";
import React from "react";
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

    if (user.isSuccess) {
        push("/");
        return;
    }

    return children;
}
