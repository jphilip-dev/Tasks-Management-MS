package com.jphilip.tm.user.exception.custom;

import lombok.Getter;
import org.springframework.validation.BindingResult;

@Getter
public class FieldErrorsException extends RuntimeException {

    private final BindingResult bindingResult;

    public FieldErrorsException(BindingResult bindingResult) {
        super();
        this.bindingResult = bindingResult;
    }
}
