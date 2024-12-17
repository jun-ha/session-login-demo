package com.example.session.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum ErrorCode {

    DUPLICATE_USERNAME(400, "ACCOUNT-001", "유저 이름이 중복된 경우");

    private final int status;
    private final String code;
    private final String description;
}
