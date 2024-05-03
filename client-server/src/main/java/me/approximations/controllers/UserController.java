package me.approximations.controllers;

import me.approximations.entities.User;
import me.approximations.security.entities.SecurityUserDetails;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {
    @GetMapping("/me")
    public User me(@AuthenticationPrincipal SecurityUserDetails userDetails) {
        return userDetails.getUser();
    }
}
