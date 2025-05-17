package com.jphilip.tm.task.exception.custom;

import com.jphilip.tm.task.ErrorCode;

public class TaskNotFoundException extends TaskException {
    public TaskNotFoundException(ErrorCode errorCode, String message) {
        super(errorCode, message);
    }
}
