package com.jphilip.tm.user.config;


import com.jphilip.tm.user.entity.Role;
import com.jphilip.tm.user.repository.RoleRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class RoleInitializer {

    private final RoleRepository roleRepository;

    @PostConstruct
    public void initRoles() {
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
