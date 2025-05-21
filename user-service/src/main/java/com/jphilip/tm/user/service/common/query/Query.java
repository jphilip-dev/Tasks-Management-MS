package com.jphilip.tm.user.service.common.query;

public interface Query<I, O>{
    O execute(I input);
}
