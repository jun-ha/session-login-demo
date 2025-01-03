package com.example.session.controller;

import com.example.session.common.Code;
import com.example.session.common.Response;
import com.example.session.config.SessionConfig;
import com.example.session.exception.DuplicateLoginException;
import com.example.session.exception.MissingSessionException;
import com.example.session.service.LoginCommand;
import com.example.session.service.RegisterCommand;
import com.example.session.service.UserService;
import com.example.session.service.session.SessionId;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final SessionConfig sessionConfig;

    @PostMapping("/register")
    public ResponseEntity<Response<Void>> register(
            @Valid @RequestBody RegisterCommand command
    ) {
        userService.register(command);

        return ResponseEntity
                .ok()
                .body(new Response<>("CREATED", null, "회원가입 성공"));
    }

    @PostMapping("/login")
    public ResponseEntity<Response<Void>> login(
            @CookieValue(value = SessionConfig.COOKIE_NAME, required = false) SessionId oldSession,
            @Valid @RequestBody LoginCommand command
    ) {
        if(oldSession != null) {
            throw new DuplicateLoginException(Code.DUPLICATE_LOGIN, "로그아웃을 먼저 진행해야합니다.");
        }

        SessionId sid = userService.login(command);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .headers(sessionConfig.createSessionCookieHeaders(sid))
                .body(new Response<>("CREATED", null, "로그인 성공"));
    }

    @PostMapping("/logout")
    public ResponseEntity<Response<Void>> logout(
            @CookieValue(value = SessionConfig.COOKIE_NAME, required = false) SessionId sid
    ) {
        if(sid == null) {
            throw new MissingSessionException(Code.MISSING_SESSION, "로그인 상태가 아닙니다.");
        }

        userService.logout(sid);

        return ResponseEntity
                .status(HttpStatus.NO_CONTENT)
                .headers(sessionConfig.deleteSessionCookieHeaders(sid))
                .body(new Response<>("OK", null, "로그아웃 성공"));
    }
}
