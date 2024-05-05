import React, { ComponentProps, useEffect, useState } from "react";
import { twMerge } from "tailwind-merge";

type WarningTypeName = "error" | "success";

interface Props extends ComponentProps<"div"> {
    open: boolean;
    delayToClose?: number;
    type?: WarningTypeName;
}

const Warnings: { [k in WarningTypeName]: string } = {
    success: "bg-green-300 text-green-800",
    error: "bg-red-300 text-red-800",
};

const Warning = ({ className, children, open, delayToClose = 10000, type = "success" }: Props) => {
    const [opened, setOpened] = useState<boolean>(open);
    const warning = Warnings[type];

    useEffect(() => {
        if (open) {
            setOpened(open);
            setTimeout(() => {
                setOpened(false);
            }, delayToClose);
        }
    }, [open, delayToClose]);

    return opened && <div className={twMerge("rounded-sm p-4", warning, className)}>{children}</div>;
};

export default Warning;
