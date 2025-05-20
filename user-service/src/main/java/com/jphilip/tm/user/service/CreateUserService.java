package com.jphilip.tm.user.service;

import com.jphilip.tm.user.dto.UserResponseDTO;
import com.jphilip.tm.user.entity.Role;
import com.jphilip.tm.user.exception.custom.FieldErrorsException;
import com.jphilip.tm.user.mapper.UserMapper;
import com.jphilip.tm.user.repository.RoleRepository;
import com.jphilip.tm.user.repository.UserRepository;
import com.jphilip.tm.user.service.util.command.Command;
import com.jphilip.tm.user.service.util.command.dto.CreateUserDTO;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.validation.FieldError;

@Service
@RequiredArgsConstructor
public class CreateUserService implements Command<CreateUserDTO, UserResponseDTO> {

    private static final String ROLE_USER = "USER";

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
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
            throw new FieldErrorsException(bindingResult);
        }

        var newUser = userMapper.toEntity(createUserDTO.userRequestDTO());

        newUser.setIsActive(true); // dev

        newUser.addRole(roleRepository.findByName(ROLE_USER).orElseThrow());
        userRepository.save(newUser);

        return userMapper.toDto(newUser);
    }
    /*
     *
     *  Helper method/s
     *
     */

    @PostConstruct
    private void init() {
        createRoleIfNotExists("USER", "User role");
        createRoleIfNotExists("ADMIN", "Admin role");
    }

    private void createRoleIfNotExists(String roleName, String description) {
        if (roleRepository.findByName(roleName).isEmpty()) {
            roleRepository.save(Role.builder()
                    .name(roleName)
                    .description(description)
                    .build());
        }
    }
}
