package com.jphilip.tm.user.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@RequiredArgsConstructor
@Getter
public enum ErrorCode {

    ERROR_EMAIL_NOT_FOUND(HttpStatus.NOT_FOUND),
    ERROR_ID_NOT_FOUND(HttpStatus.NOT_FOUND);

    private final HttpStatus httpStatus;


}
