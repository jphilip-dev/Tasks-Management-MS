package com.jphilip.tm.user.controller;

import com.jphilip.tm.user.dto.AuthRequestDTO;
import com.jphilip.tm.user.dto.AuthResponseDTO;
import com.jphilip.tm.user.service.AuthHelperService;
import jakarta.validation.Valid;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthHelperController {

    private final AuthHelperService authHelperService;

    @PostMapping
    public ResponseEntity<AuthResponseDTO> authenticate(@Valid @RequestBody AuthRequestDTO authRequestDTO) {
        return  ResponseEntity.ok(authHelperService.authenticate(authRequestDTO));
    }
}
