package com.jphilip.tm.user.dto;

import java.util.List;

public record AuthResponseDTO(Long id, String email, List<String> roles) {
}
