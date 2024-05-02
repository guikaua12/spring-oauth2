package me.approximations.exceptionhadler;

import jakarta.servlet.http.HttpServletRequest;
import me.approximations.exceptions.AppException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;

@ControllerAdvice
public class DefaultControllerAdvice {
    @ExceptionHandler(AppException.class)
    public ResponseEntity<?> handleExceptions(HttpServletRequest request, AppException exception) {
        final Map<String, Object> map = new LinkedHashMap<>();

        map.put("timestamp", new Date());
        map.put("status", exception.getStatus().value());
        map.put("error", exception.getStatus().getReasonPhrase());
        map.put("message", exception.getMessage());
        map.put("path", request.getRequestURI());

        return ResponseEntity.status(exception.getStatus()).body(map);
    }
}
