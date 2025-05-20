package com.jphilip.tm.user.service.util.command.dto;

import com.jphilip.tm.user.dto.UserRequestDTO;
import org.springframework.validation.BindingResult;

public record CreateUserDTO(UserRequestDTO userRequestDTO, BindingResult bindingResult) {
}
