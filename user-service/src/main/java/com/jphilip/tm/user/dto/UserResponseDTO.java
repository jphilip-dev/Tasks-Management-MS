package com.jphilip.tm.user.dto;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Builder
@Getter
public class UserResponseDTO {
    private Long id;
    private String email;
    private String name;
    private Boolean isActive;
    private List<String> roles;
    private String teamLead;
    private List<String> teamMembers;
}
