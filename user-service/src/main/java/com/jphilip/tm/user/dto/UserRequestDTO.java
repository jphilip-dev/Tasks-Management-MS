package com.jphilip.tm.user.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserRequestDTO {
    @Email
    @NotBlank
    private String email;

    @NotBlank
    private String name;

    @NotBlank
    private String password;
}
