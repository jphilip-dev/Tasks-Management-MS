package com.jphilip.tm.auth.dto;

import java.util.List;

public record UserDetailsResponseDTO(Long id, String email, List<String> roles) {
}
