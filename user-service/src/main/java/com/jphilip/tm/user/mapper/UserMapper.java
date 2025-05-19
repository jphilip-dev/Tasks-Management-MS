package com.jphilip.tm.user.mapper;

import com.jphilip.tm.user.dto.UserResponseDTO;
import com.jphilip.tm.user.entity.Role;
import com.jphilip.tm.user.entity.User;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserMapper {
    public UserResponseDTO toDto(User user) {
        return UserResponseDTO.builder()
                .id(user.getId())
                .email(user.getEmail())
                .name(user.getName())
                .isActive(user.getIsActive())
                .roles(user.getRoles() != null
                        ? user.getRoles().stream().map(Role::getName).toList()
                        : List.of())
                .teamLead(user.getTeamLead() != null
                        ? user.getTeamLead().getName()
                        : null)
                .teamMembers(user.getTeamMembers() != null
                        ? user.getTeamMembers().stream().map(User::getName).toList()
                        : List.of())
                .build();
    }
}

