package com.example.session.service;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;

@Getter
public class RegisterCommand {
    @NotBlank
    @Pattern(
            regexp = "^[a-zA-Z0-9]{4,12}$",
            message = "Username must be 4-12 characters long and contain only letters and numbers."
    )
    private String username;

    @NotBlank
    @Pattern(
            regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,20}$",
            message = "Password must be 8-20 characters long, include at least one uppercase letter, one lowercase letter, one number, and one special character."
    )
    private String password;
}
