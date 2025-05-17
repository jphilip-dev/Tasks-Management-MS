package com.jphilip.tm.task;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum ErrorCode {

    ERROR_TASK_NOT_FOUND(HttpStatus.NOT_FOUND),

    ERROR_USER_UNAUTHORIZED(HttpStatus.UNAUTHORIZED),
    ERROR_INVALID_INPUT(HttpStatus.BAD_REQUEST),
    ERROR_INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR);

    final HttpStatus httpStatus;

    ErrorCode(HttpStatus httpStatus){
        this.httpStatus = httpStatus;
    }

}
