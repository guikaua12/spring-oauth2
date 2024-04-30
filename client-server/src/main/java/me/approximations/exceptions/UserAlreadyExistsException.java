package me.approximations.exceptions;

import org.springframework.http.HttpStatus;

public class UserAlreadyExistsException extends AppException {
    public UserAlreadyExistsException() {
        super("User already exists.", HttpStatus.CONFLICT);
    }
}
