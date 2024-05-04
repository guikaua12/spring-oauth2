"use client";
import React, { ComponentProps } from "react";

const Button = ({ children, ...rest }: ComponentProps<"button">) => {
    return (
        <button className="rounded-lg bg-teal-500 p-4 font-semibold text-white" {...rest}>
            {children}
        </button>
    );
};

export default Button;
