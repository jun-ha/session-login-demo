package com.example.session.common;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL) //null 값 제외
public class Response<T> {
    private final String code;
    private final T data;
    private final String message;
}
