package com.example.session.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
        ErrorCode code = ErrorCode.INVALID_INPUT;
        BindingResult bindingResult = ex.getBindingResult();

        StringBuilder sb = new StringBuilder();
        for (FieldError fieldError : bindingResult.getFieldErrors()) {
            sb.append("[");
            sb.append(fieldError.getField());
            sb.append("] - ");
            sb.append(fieldError.getDefaultMessage());
            sb.append(" ");
        }

        return ResponseEntity
                .status(HttpStatus.valueOf(code.getStatus()))
                .body(new ErrorResponse(code.getCode(), sb.toString()));
    }

    @ExceptionHandler(DuplicateUsernameException.class)
    public ResponseEntity<ErrorResponse> handleUsernameAlreadyExistsException(DuplicateUsernameException e) {
        ErrorCode code = e.getErrorCode();

        return ResponseEntity
                .status(HttpStatus.valueOf(code.getStatus()))
                .body(new ErrorResponse(code.getCode(), e.getMessage()));
    }
}
