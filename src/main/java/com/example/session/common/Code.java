package com.example.session.common;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum Code {
    INVALID_INPUT("INVALID_INPUT", "유효성 검증 실패 시"),
    DUPLICATE_USERNAME("DUPLICATE_USERNAME", "유저 이름이 중복된 경우"),
    USER_NOT_FOUND("USER_NOT_FOUND", "유저 이름을 찾을 수 없는 경우"),
    WRONG_PASSWORD("WRONG_PASSWORD", "비밀번호가 잘못된 경우"),
    DUPLICATE_LOGIN("DUPLICATE_LOGIN", "중복 로그인 시도할 경우"),
    MISSING_SESSION("MISSING_SESSION", "세션이 없는 경우");

    private final String code;
    private final String description;
}
