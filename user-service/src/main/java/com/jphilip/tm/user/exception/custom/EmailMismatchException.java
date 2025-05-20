package com.jphilip.tm.user.exception.custom;

import com.jphilip.tm.user.exception.ErrorCode;

public class EmailMismatchException extends AbstractUserException {
    public EmailMismatchException(String message) {
        super(ErrorCode.ERROR_EMAIL_MISMATCH, message);
    }
    public EmailMismatchException(){
        this(null);
    }
}
