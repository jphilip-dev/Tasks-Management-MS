package com.jphilip.tm.task.exception.custom;

import com.jphilip.tm.task.enums.ErrorCode;

public class TaskNotFoundException extends TaskException {
    public TaskNotFoundException(ErrorCode errorCode, String message) {
        super(errorCode, message);
    }
}
