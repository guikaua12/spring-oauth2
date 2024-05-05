import { useContext } from "react";
import { AuthContext } from "@/feature/auth/providers/AuthProvider";

export const useAuth = () => {
    return useContext(AuthContext);
};
