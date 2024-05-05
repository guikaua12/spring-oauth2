"use client";
import React, { ComponentProps } from "react";
import Image from "next/image";
import { useAuth } from "@/feature/auth/hooks/useAuth";
import UserHeaderMenu from "@/feature/auth/UserHeaderMenu/user-header-menu";

const Header = ({}: ComponentProps<"header">) => {
    const { user } = useAuth();

    return (
        <header className="flex bg-white p-2">
            <div className="container mx-auto flex items-center justify-between">
                <div className="flex items-center gap-2">
                    <Image src="/logo.png" width={64} height={64} alt="Logo" />
                    <span className="text-2xl font-black text-orange-950">Front-end</span>
                </div>

                {user.isSuccess && <UserHeaderMenu user={user.data} />}
            </div>
        </header>
    );
};

export default Header;
