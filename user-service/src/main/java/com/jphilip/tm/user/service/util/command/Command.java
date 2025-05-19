package com.jphilip.tm.user.service.util.command;

public interface Command <I, O>{
    O execute(I input);
}
