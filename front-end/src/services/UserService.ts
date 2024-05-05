import { api } from "@/services/api";

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
