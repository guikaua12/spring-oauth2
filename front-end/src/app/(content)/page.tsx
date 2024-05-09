"use client";
import Cookies from "js-cookie";
import { useEffect, useState } from "react";

export default function Home() {
    const [token, setToken] = useState<string | undefined>(undefined);

    useEffect(() => {
        setToken(Cookies.get("token"));
    }, []);

    return <main className="p-4"></main>;
}
