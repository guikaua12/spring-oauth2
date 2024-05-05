export type User = {
    id: number;
    name: string;
    email: string;
    accountType: "SELF" | "GOOGLE";
    imageUrl: string;
};
