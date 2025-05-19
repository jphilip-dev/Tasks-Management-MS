package com.jphilip.tm.user.service.util;

import org.springframework.http.ResponseEntity;

public interface Query<I, O>{
    O execute(I input);
}
