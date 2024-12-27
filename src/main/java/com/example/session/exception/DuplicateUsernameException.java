package com.example.session.exception;

import com.example.session.common.Code;
import lombok.Getter;

@Getter
public class DuplicateUsernameException extends RuntimeException {
    private final Code errorCode;

    public DuplicateUsernameException(Code code, String message) {
        super(message);
        this.errorCode = code;
    }
}
