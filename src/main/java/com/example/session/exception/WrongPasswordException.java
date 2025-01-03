package com.example.session.exception;

import com.example.session.common.Code;

public class WrongPasswordException extends CustomException {
    public WrongPasswordException(Code code, String message) {
        super(code, message);
    }
}
