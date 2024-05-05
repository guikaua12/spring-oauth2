export type ApiError = {
    readonly timestamp: string;
    readonly status: number;
    readonly error: string;
    readonly message: string;
    readonly path: string;
};
