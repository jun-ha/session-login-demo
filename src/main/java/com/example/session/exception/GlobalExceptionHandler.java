package com.example.session.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(DuplicateUsernameException.class)
    public ResponseEntity<ErrorResponse> handleUsernameAlreadyExistsException(DuplicateUsernameException e) {
        ErrorCode code = e.getErrorCode();

        return ResponseEntity
                .status(HttpStatus.valueOf(code.getStatus()))
                .body(new ErrorResponse(code.getCode(), e.getMessage()));
    }
}
