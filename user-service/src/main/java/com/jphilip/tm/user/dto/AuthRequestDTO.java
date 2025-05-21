package com.jphilip.tm.user.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;


@AllArgsConstructor
@NoArgsConstructor
@Getter
public class AuthRequestDTO {
    @NotBlank
    private String email;
    @NotBlank
    private String password;
}
