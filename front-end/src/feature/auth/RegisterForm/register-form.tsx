"use client";
import React from "react";
import Input from "@/components/Input/input";
import Link from "next/link";
import Button from "@/components/Button/button";
import { z } from "zod";
import { useForm } from "react-hook-form";
import { zodResolver } from "@hookform/resolvers/zod";
import { useAuth } from "@/feature/auth/hooks/useAuth";
import { useRouter } from "next/navigation";

const RegisterSchema = z.object({
    name: z.string().min(1),
    email: z.string().email(),
    password: z.string().min(1),
});

type RegisterSchemaType = z.infer<typeof RegisterSchema>;

const RegisterForm = () => {
    const { register, handleSubmit } = useForm<RegisterSchemaType>({
        resolver: zodResolver(RegisterSchema),
    });
    const { register: authRegister, login } = useAuth();
    const { push } = useRouter();

    const onRegister = ({ email, password }: RegisterSchemaType) => {
        login.mutate(
            { email, password },
            {
                onSuccess: () => {
                    push("/");
                },
            }
        );
    };

    const onSubmit = ({ name, email, password }: RegisterSchemaType) => {
        authRegister.mutate(
            { name, email, password },
            {
                onSuccess: () => onRegister({ name, email, password }),
            }
        );
    };

    return (
        <form className="flex flex-col" onSubmit={handleSubmit(onSubmit)}>
            <Input placeholder="Name" type="text" className="mb-2" hookFormRegister={() => register("name")} />
            <Input placeholder="Email" type="email" className="mb-2" hookFormRegister={() => register("email")} />
            <Input
                placeholder="Password"
                type="password"
                className="mb-0.5"
                hookFormRegister={() => register("password")}
            />

            <span className="mb-2 text-sm">
                <span>Have an account? </span>
                <Link className="font-semibold text-teal-500" href="/auth/login">
                    Sign in!
                </Link>
            </span>

            <Button>SIGN UP</Button>
        </form>
    );
};

export default RegisterForm;
