package me.approximations.config;

import me.approximations.dtos.UserLoginDTO;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.NoOpAuthenticationEntryPoint;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .oauth2Login(c ->
                        c.loginPage("/") // disable login page
                                .successHandler((request, response, authentication) -> {
                                    System.out.println();
                                })
                )
                .authorizeHttpRequests(c ->
                        c.requestMatchers("/auth/**").anonymous()
                                .anyRequest().authenticated()
                )
                .csrf(AbstractHttpConfigurer::disable)
                .exceptionHandling(c -> c.authenticationEntryPoint(new NoOpAuthenticationEntryPoint())) // If i remove this, for some reason Method Not Allowed (405) exceptions are not catch :/
                .build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
