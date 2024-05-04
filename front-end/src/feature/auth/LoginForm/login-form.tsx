"use client";
import React from "react";
import Input from "@/components/Input/input";
import Link from "next/link";
import Button from "@/components/Button/button";
import { z } from "zod";
import { useForm } from "react-hook-form";
import { zodResolver } from "@hookform/resolvers/zod";
import { login } from "@/services/UserService";
import { useRouter } from "next/navigation";

const LoginSchema = z.object({
    email: z.string().email(),
    password: z.string().min(1),
});

type LoginSchemaType = z.infer<typeof LoginSchema>;

const LoginForm = () => {
    const { register, handleSubmit } = useForm<LoginSchemaType>({
        resolver: zodResolver(LoginSchema),
    });

    const { push } = useRouter();

    const onSubmit = ({ email, password }: LoginSchemaType) => {
        login({ email, password }).then(() => {
            push("/");
        });
    };

    return (
        <form className="flex flex-col" onSubmit={handleSubmit(onSubmit)}>
            <Input placeholder="Email" type="email" className="mb-2" hookFormRegister={() => register("email")} />
            <Input
                placeholder="Password"
                type="password"
                className="mb-0.5"
                hookFormRegister={() => register("password")}
            />

            <span className="mb-2 text-sm">
                <span>Don’t have an account? </span>
                <Link className="font-semibold text-teal-500" href="/auth/register">
                    Sign up!
                </Link>
            </span>

            <Button>LOG IN</Button>
        </form>
    );
};

export default LoginForm;
