package com.jphilip.tm.user.exception.custom;

import com.jphilip.tm.user.exception.ErrorCode;

public class UserIdMismatchException extends AbstractUserException {

    public UserIdMismatchException(String message) {
        super(ErrorCode.ERROR_ID_MISMATCH,message);
    }

    public UserIdMismatchException(){
        this(null);
    }
}
