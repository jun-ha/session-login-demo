package com.example.session.exception;

import lombok.Getter;

@Getter
public class DuplicateUsernameException extends RuntimeException {
    private final ErrorCode errorCode;

    public DuplicateUsernameException(ErrorCode code, String message) {
        super(message);
        this.errorCode = code;
    }
}
