"use client";
import Cookies from "js-cookie";
import { useEffect, useState } from "react";

export default function Home() {
    const [token, setToken] = useState<string | undefined>(undefined);

    useEffect(() => {
        setToken(Cookies.get("token"));
    }, []);

    return (
        <main className="p-4">
            <p>{JSON.stringify(token)}</p>
            {!token && (
                <a
                    onClick={() => {
                        const newWindow = window.open(
                            "http://localhost:8080/oauth2/authorization/google",
                            "name",
                            "width=480,height=320"
                        );

                        if (newWindow) {
                            newWindow.focus();
                        }
                    }}
                >
                    Login with google
                </a>
            )}
        </main>
    );
}
