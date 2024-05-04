"use client";
import React from "react";
import googleIcon from "@/../public/icons/google.svg";
import Image from "next/image";

type ProviderName = "google";

type Provider = {
    name: string;
    url: string;
    icon: any;
};

interface Props {
    providerName: ProviderName;
}

const Providers: { [k in ProviderName]: Provider } = {
    google: {
        name: "Google",
        url: "http://localhost:8080/oauth2/authorization/google",
        icon: googleIcon,
    },
};

const AuthProviderButton = ({ providerName }: Props) => {
    const provider = Providers[providerName];

    const onClick = () => {
        window.open(provider.url, "name", "width=480,height=640");
    };

    return (
        <button
            className="flex items-center gap-3 rounded-md border-2 border-neutral-200 bg-white px-4 py-3 hover:border-teal-500"
            onClick={onClick}
        >
            <Image src={provider.icon} width={20} height={20} alt={provider.name}></Image>
            <span className="font-medium">{provider.name}</span>
        </button>
    );
};

export default AuthProviderButton;
