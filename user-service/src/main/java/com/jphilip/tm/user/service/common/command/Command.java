package com.jphilip.tm.user.service.common.command;

public interface Command <I, O>{
    O execute(I input);
}
