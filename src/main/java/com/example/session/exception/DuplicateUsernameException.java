package com.example.session.exception;

import com.example.session.common.Code;
import lombok.Getter;

public class DuplicateUsernameException extends CustomException {
    public DuplicateUsernameException(Code code, String message) {
        super(code, message);
    }
}
