package com.jphilip.tm.user.service;

import com.jphilip.tm.user.dto.UserRequestDTO;
import com.jphilip.tm.user.dto.UserResponseDTO;
import com.jphilip.tm.user.entity.Role;
import com.jphilip.tm.user.mapper.UserMapper;
import com.jphilip.tm.user.repository.RoleRepository;
import com.jphilip.tm.user.repository.UserRepository;
import com.jphilip.tm.user.service.util.command.Command;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CreateUserService implements Command<UserRequestDTO, UserResponseDTO> {

    private static final String ROLE_USER = "USER";

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final UserMapper userMapper;

    @Override
    public UserResponseDTO execute(UserRequestDTO userRequestDTO) {

        var newUser = userMapper.toEntity(userRequestDTO);

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
