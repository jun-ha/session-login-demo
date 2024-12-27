package com.example.session.exception;

import com.example.session.common.Code;
import com.example.session.common.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Response<Void>> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
        Code code = Code.INVALID_INPUT;
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
                .ok()
                .body(new Response<>(code.getCode(), null, sb.toString()));
    }

    @ExceptionHandler(DuplicateUsernameException.class)
    public ResponseEntity<Response<Void>> handleUsernameAlreadyExistsException(DuplicateUsernameException e) {
        Code code = e.getErrorCode();

        return ResponseEntity
                .ok()
                .body(new Response<>(code.getCode(), null, e.getMessage()));
    }
}
