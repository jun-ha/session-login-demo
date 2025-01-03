package com.example.session.exception;

import com.example.session.common.Code;

public class DuplicateLoginException extends CustomException {
    public DuplicateLoginException(Code code, String message) {
        super(code, message);
    }
}
