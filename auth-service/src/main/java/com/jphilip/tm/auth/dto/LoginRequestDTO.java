package com.jphilip.tm.auth.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginRequestDTO {

    @NotBlank
    @Email(message = "Email should be a valid email")
    private String email;

    @NotBlank
    @Size(min = 8, message = "Password must at least 8 characters long")
    private String password;
}
