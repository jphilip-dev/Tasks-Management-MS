package com.jphilip.tm.user.service.util.command.dto;

import com.jphilip.tm.user.dto.UserRequestDTO;

public record UpdateUserDTO(Long id, UserRequestDTO userRequestDTO) {
}
