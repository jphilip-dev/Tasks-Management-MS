package com.jphilip.tm.user.service.util;

import org.springframework.http.ResponseEntity;

public interface Command <I, O>{
    O execute(I input);
}
