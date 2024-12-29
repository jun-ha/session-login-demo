package com.example.session.common;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum Code {
    INVALID_INPUT("COMMON-001", "유효성 검증 실패 시"),

    DUPLICATE_USERNAME("ACCOUNT-001", "유저 이름이 중복된 경우");

    private final String code;
    private final String description;
}
