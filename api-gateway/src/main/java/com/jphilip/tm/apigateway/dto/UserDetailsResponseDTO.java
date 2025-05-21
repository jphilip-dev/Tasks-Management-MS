package com.jphilip.tm.apigateway.dto;

import java.util.List;

public record UserDetailsResponseDTO(Long id, String email, List<String> roles) {
}
