package com.jphilip.tm.user.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@RequiredArgsConstructor
@Getter
public enum ErrorCode {

    ERROR_EMAIL_NOT_FOUND(HttpStatus.NOT_FOUND),
    ERROR_EMAIL_MISMATCH(HttpStatus.BAD_REQUEST),
    ERROR_ID_NOT_FOUND(HttpStatus.NOT_FOUND),
    ERROR_ID_MISMATCH(HttpStatus.BAD_REQUEST),
    ERROR_FIELD_VALUE(HttpStatus.BAD_REQUEST);

    private final HttpStatus httpStatus;
}
