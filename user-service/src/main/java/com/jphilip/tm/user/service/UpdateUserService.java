package com.jphilip.tm.user.service;

import com.jphilip.tm.user.dto.UserResponseDTO;
import com.jphilip.tm.user.exception.custom.EmailMismatchException;
import com.jphilip.tm.user.exception.custom.FieldErrorException;
import com.jphilip.tm.user.exception.custom.UserIdMismatchException;
import com.jphilip.tm.user.mapper.UserMapper;
import com.jphilip.tm.user.repository.UserRepository;
import com.jphilip.tm.user.service.common.command.Command;
import com.jphilip.tm.user.service.common.command.dto.UpdateUserDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UpdateUserService implements Command<UpdateUserDTO, UserResponseDTO> {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserServiceHelper userServiceHelper;
    private final UserMapper userMapper;


    @Override
    public UserResponseDTO execute(UpdateUserDTO updateUserDTO) {

        // Extract Data
        var clientId =updateUserDTO.clientId();
        var id = updateUserDTO.id();
        var userRequestDTO = updateUserDTO.userRequestDTO();
        var bindingResult = updateUserDTO.bindingResult();

        // To maintain uniformity as other service use this exception instead of MethodArgumentValEx....
        if (bindingResult.hasErrors()){
            throw new FieldErrorException(bindingResult);
        }

        // check ownership
        if (!clientId.equals(id)){
            throw new UserIdMismatchException();
        }

        // Retrieve existing user
        var user = userServiceHelper.validateUserById(id);

        // Check existing and update request email is matching NOTE: EMAIL is final
        if (!user.getEmail().equals(userRequestDTO.getEmail())){
            throw new EmailMismatchException();
        }

        // Update user fields base on the userRequestDTO
        user.setName(userRequestDTO.getName());
        user.setPassword( passwordEncoder.encode(userRequestDTO.getPassword()));

        // validate team lead by id and set user as its member
        if (userRequestDTO.getTeamLeadId() != null) {
            var teamLead = userServiceHelper.validateUserById(userRequestDTO.getTeamLeadId());
            teamLead.addTeamMember(user);
        }

        userRepository.save(user);

        return userMapper.toDto(user);
    }
}
