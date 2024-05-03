package me.approximations.controllers;

import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import me.approximations.dtos.UserLoginDTO;
import me.approximations.dtos.UserRegisterDTO;
import me.approximations.entities.User;
import me.approximations.security.jwt.JwtTokenCookieHeaderSetter;
import me.approximations.security.services.CustomUserDetailsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/auth")
public class AuthController {
    private final CustomUserDetailsService userDetailsService;
    private final JwtTokenCookieHeaderSetter tokenCookieSetter;

    @PostMapping("/register")
    public User register(@RequestBody @Valid UserRegisterDTO dto) {
        return userDetailsService.registerUser(dto);
    }

    @PostMapping("/login")
    public String login(@RequestBody @Valid UserLoginDTO dto, HttpServletResponse response) {
        final String token = userDetailsService.login(dto);

        tokenCookieSetter.set(response, token);

        return token;
    }
}
