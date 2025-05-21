package com.jphilip.tm.user.service;

import com.jphilip.tm.user.dto.UserResponseDTO;
import com.jphilip.tm.user.exception.custom.FieldErrorException;
import com.jphilip.tm.user.mapper.UserMapper;
import com.jphilip.tm.user.repository.RoleRepository;
import com.jphilip.tm.user.repository.UserRepository;
import com.jphilip.tm.user.service.common.command.Command;
import com.jphilip.tm.user.service.common.command.dto.CreateUserDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.validation.FieldError;


@Service
@RequiredArgsConstructor
public class CreateUserService implements Command<CreateUserDTO, UserResponseDTO> {

    private static final String ROLE_USER = "USER";

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserServiceHelper userServiceHelper;
    private final UserMapper userMapper;


    @Override
    public UserResponseDTO execute(CreateUserDTO createUserDTO) {

        var userRequestDTO = createUserDTO.userRequestDTO();
        var bindingResult = createUserDTO.bindingResult();

        if(!bindingResult.hasErrors() && userRepository.findByEmail(userRequestDTO.getEmail()).isPresent()){
            bindingResult.addError(new FieldError("Email", "email", "Email already exists"));
        }

        // check binding result
        if (bindingResult.hasErrors()){
            throw new FieldErrorException(bindingResult);
        }

        var newUser = userMapper.toEntity(createUserDTO.userRequestDTO());

        // Parse Password, add role and set status to false(inactive)
        newUser.setPassword(passwordEncoder.encode(userRequestDTO.getPassword()));
        newUser.addRole(roleRepository.findByName(ROLE_USER).orElseThrow());
        newUser.setIsActive(false);

        // validate team lead by id and set user as its member
        var teamLead = userServiceHelper.validateUserById(userRequestDTO.getTeamLeadId());
        teamLead.addTeamMember(newUser);

        userRepository.save(newUser);

        return userMapper.toDto(newUser);
    }
}
