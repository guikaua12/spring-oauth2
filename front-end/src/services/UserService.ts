import { api } from "@/services/api";

type LoginParams = {
    email: string;
    password: string;
};

export async function login({ email, password }: LoginParams): Promise<void> {
    await api.post("/auth/login", { email, password }); /* will automatically set token cookie if success */
}
