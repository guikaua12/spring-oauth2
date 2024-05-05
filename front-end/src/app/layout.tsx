import React from "react";
import type { Metadata } from "next";
import { Inter } from "next/font/google";
import "./globals.css";
import QueryProvider from "@/feature/query/providers/QueryProvider";
import { AuthProvider } from "@/feature/auth/providers/AuthProvider";
import WithAxios from "@/components/WithAxios/with-axios";

const inter = Inter({ subsets: ["latin"] });

export const metadata: Metadata = {
    title: "Front-end",
};

export default function RootLayout({
    children,
}: Readonly<{
    children: React.ReactNode;
}>) {
    return (
        <html lang="pt-br">
            <body className={inter.className}>
                <QueryProvider>
                    <AuthProvider>
                        <WithAxios>{children}</WithAxios>
                    </AuthProvider>
                </QueryProvider>
            </body>
        </html>
    );
}
