package com.example.session.service;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class LoginCommand {
    @NotBlank
    private String username;

    @NotBlank
    private String password;
}
