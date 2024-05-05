import { api } from "@/services/api";
import { User } from "@/types/User";

export type LoginParams = {
    email: string;
    password: string;
};

export async function login({ email, password }: LoginParams): Promise<void> {
    await api.post("/auth/login", { email, password }); /* will automatically set token cookie if success */
}

export type RegisterParams = {
    name: string;
    email: string;
    password: string;
};

export async function register({ name, email, password }: RegisterParams): Promise<void> {
    await api.post("/auth/register", { name, email, password });
}

export async function userInfo(): Promise<User> {
    const response = await api.get("/user/me");
    return response.data;
}
