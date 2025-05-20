package com.jphilip.tm.user.exception.custom;

import com.jphilip.tm.user.exception.ErrorCode;
import lombok.Getter;

@Getter
public abstract class AbstractUserException extends RuntimeException {

    private final ErrorCode errorCode;

    public AbstractUserException(ErrorCode  errorCode, String message) {
        super(message);
        this.errorCode = errorCode;
    }
}
