package com.jphilip.tm.auth.controller;

import com.jphilip.tm.auth.dto.LoginRequestDTO;
import com.jphilip.tm.auth.dto.LoginResponseDTO;
import com.jphilip.tm.auth.dto.UserDetailsResponseDTO;
import com.jphilip.tm.auth.service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> login(@Valid @RequestBody LoginRequestDTO loginRequestDTO){
        String token = authService.authenticate(loginRequestDTO);
        return ResponseEntity.accepted().body(new LoginResponseDTO(token));
    }

    @GetMapping("/validate")
    public ResponseEntity<UserDetailsResponseDTO> validate(@RequestHeader(value = "Authorization", required = false) String authHeader){
        return ResponseEntity.ok(authService.validateToken(authHeader));
    }

}
