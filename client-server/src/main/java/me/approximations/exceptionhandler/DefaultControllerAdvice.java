package me.approximations.exceptionhandler;

import me.approximations.error.ApiError;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class DefaultControllerAdvice {
    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<?> handleExceptions(RuntimeException exception) {
        final ApiError apiError = new ApiError(exception);
        return ResponseEntity.status(apiError.getStatus()).body(apiError);
    }

    @ExceptionHandler(AuthenticationException.class)
    public ResponseEntity<?> handleAuthExceptions(AuthenticationException exception) {
        final ApiError apiError = new ApiError(HttpStatus.UNAUTHORIZED, exception.getMessage());
        return ResponseEntity.status(apiError.getStatus()).body(apiError);
    }
}
