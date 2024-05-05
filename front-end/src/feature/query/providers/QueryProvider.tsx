"use client";
import React, { ReactNode, useState } from "react";
import { QueryClient, QueryClientProvider } from "@tanstack/react-query";

const QueryProvider = ({ children }: { children: ReactNode }) => {
    const [client, setClient] = useState<QueryClient>(new QueryClient());

    return <QueryClientProvider client={client}>{children}</QueryClientProvider>;
};

export default QueryProvider;
