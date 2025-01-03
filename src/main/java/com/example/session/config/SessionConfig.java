package com.example.session.config;

import com.example.session.service.session.SessionId;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.stereotype.Component;

@Component
public class SessionConfig {

    public static final String COOKIE_NAME = "SESSION";
    public static final boolean HTTP_ONLY = true;
    public static final boolean SECURE = true;
    public static final String PATH = "/";
    public static final long MAX_AGE = 3600; // 1시간

    public HttpHeaders createSessionCookieHeaders(SessionId sid) {
        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.SET_COOKIE,
                ResponseCookie.from(COOKIE_NAME, sid.toString())
                        .httpOnly(HTTP_ONLY)
                        .path(PATH)
                        .maxAge(MAX_AGE)
                        .build()
                        .toString()
        );

        return headers;
    }

    public HttpHeaders deleteSessionCookieHeaders(SessionId sid) {
        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.SET_COOKIE,
                ResponseCookie.from(COOKIE_NAME, sid.toString())
                        .httpOnly(HTTP_ONLY)
                        .path(PATH)
                        .maxAge(0)
                        .build()
                        .toString()
        );

        return headers;
    }

}

