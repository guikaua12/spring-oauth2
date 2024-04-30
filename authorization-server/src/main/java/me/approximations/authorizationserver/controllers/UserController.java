package me.approximations.authorizationserver.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {
    @GetMapping("/user1")
    public String demo() {
        final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return String.format("User1, %s", authentication.getName());
    }
}
