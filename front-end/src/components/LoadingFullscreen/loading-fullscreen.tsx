import React from "react";
import { LoaderCircle } from "lucide-react";

const LoadingFullscreen = () => {
    return (
        <div className="flex h-screen items-center justify-center">
            <LoaderCircle className="animate-spin" size={48} color="#14b8a6" />
        </div>
    );
};

export default LoadingFullscreen;
