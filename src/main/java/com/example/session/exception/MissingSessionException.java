package com.example.session.exception;

import com.example.session.common.Code;

public class MissingSessionException extends CustomException {
    public MissingSessionException(Code code, String message) {
        super(code, message);
    }
}
