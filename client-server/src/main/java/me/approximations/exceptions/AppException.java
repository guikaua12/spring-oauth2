package me.approximations.exceptions;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@RequiredArgsConstructor
@Getter
public abstract class AppException extends RuntimeException {
    private final String message;
    private final HttpStatus status;
}
