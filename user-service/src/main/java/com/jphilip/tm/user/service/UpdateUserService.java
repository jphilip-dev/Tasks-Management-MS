package com.jphilip.tm.user.service;

import com.jphilip.tm.user.dto.UserResponseDTO;
import com.jphilip.tm.user.service.util.command.Command;
import com.jphilip.tm.user.service.util.command.dto.UpdateUserDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UpdateUserService implements Command<UpdateUserDTO, UserResponseDTO> {

    @Override
    public UserResponseDTO execute(UpdateUserDTO updateUserDTO) {
        return null;
    }
}
