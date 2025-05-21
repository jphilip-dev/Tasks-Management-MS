package com.jphilip.tm.auth.service;

import com.jphilip.tm.auth.dto.LoginRequestDTO;
import com.jphilip.tm.auth.dto.UserDetailsResponseDTO;
import com.jphilip.tm.auth.exception.custom.UnauthorizedException;
import com.jphilip.tm.auth.util.JwtUtil;
import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatusCode;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final WebClient webClient;
    private final JwtUtil jwtUtil;
    private final PasswordEncoder passwordEncoder;

    public String authenticate(LoginRequestDTO loginRequestDTO){

        try {
            UserDetailsResponseDTO user = webClient.post()
                    .uri("/auth")
                    .bodyValue(loginRequestDTO)
                    .retrieve()
                    .onStatus(status -> !status.is2xxSuccessful(),
                            res -> Mono.error(new UnauthorizedException("Invalid credentials or user not found")))
                    .bodyToMono(UserDetailsResponseDTO.class)
                    .block();

            // generate token
            return jwtUtil.generateToken(user.id(), user.email(), user.roles());

        } catch (Exception e) {
            throw new RuntimeException(e); // placeholder, throw a custom exception Unauthorized exception
        }
    }

    public UserDetailsResponseDTO validateToken(String authHeader){
        if(authHeader == null || !authHeader.startsWith("Bearer ")){
            throw new IllegalArgumentException(); // placeholder
        }

        Claims claims = jwtUtil.validateToken(authHeader.substring(7));

        String email = claims.getSubject(); // from subject
        Long id = claims.get("id", Long.class);
        String rolesStr = claims.get("roles", String.class);

        List<String> roles = (rolesStr != null && !rolesStr.isEmpty())
                ? Arrays.asList(rolesStr.split(","))
                : new ArrayList<>();

        return new UserDetailsResponseDTO(id, email, roles);
    }
}
