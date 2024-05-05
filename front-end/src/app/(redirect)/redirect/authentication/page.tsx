"use client";
import React, { useEffect } from "react";

const Page = () => {
    useEffect(() => {
        if (window.opener) {
            window.opener.location.href = "/";
            window.close();
        }
    }, []);

    return <div>Loading...</div>;
};

export default Page;
