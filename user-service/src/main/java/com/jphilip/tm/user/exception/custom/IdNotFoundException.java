package com.jphilip.tm.user.exception.custom;

import com.jphilip.tm.user.exception.ErrorCode;

public class IdNotFoundException extends AbstractUserException {
    public IdNotFoundException(String message) {
        super(ErrorCode.ERROR_ID_NOT_FOUND, message);
    }

    public IdNotFoundException(){
        this(null);
    }
}
