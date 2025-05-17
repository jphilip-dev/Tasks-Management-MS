package com.jphilip.tm.task.exception.custom;

import com.jphilip.tm.task.ErrorCode;
import lombok.Getter;

@Getter
public abstract class TaskException extends RuntimeException {

    private final ErrorCode errorCode;

    public TaskException(ErrorCode errorCode, String message) {
        super(message);
        this.errorCode = errorCode;
    }

}
