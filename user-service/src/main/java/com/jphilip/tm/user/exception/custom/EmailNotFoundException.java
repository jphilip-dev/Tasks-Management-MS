package com.jphilip.tm.user.exception.custom;

import com.jphilip.tm.user.exception.ErrorCode;

public class EmailNotFoundException extends AbstractUserException {

  public EmailNotFoundException(String message) {
    super(ErrorCode.ERROR_EMAIL_NOT_FOUND,message);
  }

  public EmailNotFoundException(){
    this(null);
  }

}
