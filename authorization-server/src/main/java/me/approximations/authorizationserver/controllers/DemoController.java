package me.approximations.authorizationserver.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/demo")
public class DemoController {
    @GetMapping("/demo1")
    public ResponseEntity<String> demo() {
        final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        return String.format("Demo1, %s", authentication.getName());
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(String.format("Demo1, %s", authentication.getName()));
    }

    @GetMapping("/demo2")
    public String demo2() {
        final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return String.format("Demo2, %s", authentication.getName());
    }
}
