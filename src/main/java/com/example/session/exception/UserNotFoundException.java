package com.example.session.exception;

import com.example.session.common.Code;

public class UserNotFoundException extends CustomException {
    public UserNotFoundException(Code code, String message) {
        super(code, message);
    }
}
