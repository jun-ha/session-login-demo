package com.example.session.controller;

import com.example.session.service.RegisterCommand;
import com.example.session.service.RegisterUserUseCase;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/register")
@RequiredArgsConstructor
public class RegisterUserController {

    private final RegisterUserUseCase registerUserUseCase;

    @PostMapping
    public ResponseEntity<Void> register(
            @Valid @RequestBody RegisterCommand command //RegisterRequest -> RegisterCommand ?
    ) {
        registerUserUseCase.register(command);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .build();
    }
}
