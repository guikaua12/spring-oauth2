package me.approximations.config;

import me.approximations.security.handlers.CustomAuthenticationEntryPoint;
import me.approximations.security.handlers.Oauth2SuccessLoginHandler;
import me.approximations.security.jwt.filter.JwtTokenFilter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Collections;
import java.util.List;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http, Oauth2SuccessLoginHandler oauth2SuccessLoginHandler,
                                                   JwtTokenFilter jwtTokenFilter, CustomAuthenticationEntryPoint authenticationEntryPoint
    ) throws Exception {
        return http
                .cors(Customizer.withDefaults())
                .addFilterBefore(jwtTokenFilter, UsernamePasswordAuthenticationFilter.class)
                .oauth2Login(c ->
                        c.loginPage("http://localhost:3000/login") // disable login page
                                .successHandler(oauth2SuccessLoginHandler)
                )
                .authorizeHttpRequests(c ->
                        c.requestMatchers("/auth/**").anonymous()
                                .requestMatchers("/user/me").authenticated()
                                .requestMatchers("/error").permitAll()
                                .anyRequest().authenticated()
                )
                .exceptionHandling(c -> c.authenticationEntryPoint(authenticationEntryPoint))
                .csrf(AbstractHttpConfigurer::disable)
                .build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource(@Value("${spring.security.cors.origin}") String corsOrigin) {
        final CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(Collections.singletonList(corsOrigin));
        configuration.setAllowedMethods(List.of("*"));
        configuration.setAllowedHeaders(List.of("*"));
        configuration.setAllowCredentials(true);
        final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }

}
