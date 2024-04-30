package me.approximations.error;

import lombok.Getter;
import me.approximations.exceptions.AppException;
import org.springframework.http.HttpStatus;

@Getter
public class ApiError {
    private final int status;
    private final String message;

    public ApiError(HttpStatus status, String message) {
        this.status = status.value();
        this.message = message;
    }

    public ApiError(AppException appException) {
        this(appException.getStatus(), appException.getMessage());
    }

    public ApiError(Exception exception) {
        this.message = exception.getMessage();

        if (exception instanceof AppException appException) {
            this.status = appException.getStatus().value();
            return;
        }

        this.status = HttpStatus.INTERNAL_SERVER_ERROR.value();
    }

}
