package com.jphilip.tm.user.exception.custom;

import lombok.Getter;
import org.springframework.validation.BindingResult;

@Getter
public class FieldErrorException extends RuntimeException {

    private final BindingResult bindingResult;

    public FieldErrorException(BindingResult bindingResult) {
        super();
        this.bindingResult = bindingResult;
    }
}
