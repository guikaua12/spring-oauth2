"use client";
import React, { ComponentProps } from "react";
import { twMerge } from "tailwind-merge";

interface Props extends ComponentProps<"input"> {
    hookFormRegister?: () => any;
}

const Input = ({ className, hookFormRegister = () => {}, ...props }: Props) => {
    return (
        <input
            className={twMerge(
                "rounded-lg border border-zinc-300 bg-white p-5 text-sm text-zinc-600 placeholder-zinc-600 outline-0 focus:border-teal-500",
                className
            )}
            {...hookFormRegister()}
            {...props}
        />
    );
};

export default Input;
