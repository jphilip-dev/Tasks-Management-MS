package com.jphilip.tm.user.service;

import com.jphilip.tm.user.dto.AuthRequestDTO;
import com.jphilip.tm.user.dto.AuthResponseDTO;
import com.jphilip.tm.user.entity.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthHelperService {

    private final UserServiceHelper userServiceHelper;
    private final PasswordEncoder passwordEncoder;

    public AuthResponseDTO authenticate(AuthRequestDTO authRequestDTO){

        var user = userServiceHelper.validateUserByEmail(authRequestDTO.getEmail());

        if(!passwordEncoder.matches(authRequestDTO.getPassword(), user.getPassword())){
            throw new RuntimeException(); // placeholder
        }

        List<String> roles = user.getRoles().stream()
                .map(Role::getName)
                .toList();

        return  new AuthResponseDTO(user.getId(), user.getEmail(), roles);

    }
}
