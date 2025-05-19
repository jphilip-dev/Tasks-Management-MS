package com.jphilip.tm.user.service.util.query;

public interface Query<I, O>{
    O execute(I input);
}
