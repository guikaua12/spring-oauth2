import React from "react";
import { User } from "@/types/User";
import Image from "next/image";
import { LogOut } from "lucide-react";
import { useAuth } from "@/feature/auth/hooks/useAuth";

interface Props {
    user: User;
}

const UserHeaderMenu = ({ user }: Props) => {
    const { logout } = useAuth();

    return (
        <div className="flex items-center gap-4">
            <span>Hello, {user.name}</span>
            <Image
                src={
                    user.imageUrl ||
                    "https://t3.ftcdn.net/jpg/03/53/11/00/360_F_353110097_nbpmfn9iHlxef4EDIhXB1tdTD0lcWhG9.jpg"
                }
                width={36}
                height={36}
                alt="User image"
                className="rounded-full"
            />

            <button title="Logout" onClick={logout}>
                <LogOut size={18} />
            </button>
        </div>
    );
};

export default UserHeaderMenu;
