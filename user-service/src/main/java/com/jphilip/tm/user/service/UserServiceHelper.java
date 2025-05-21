package com.jphilip.tm.user.service;

import com.jphilip.tm.user.entity.User;
import com.jphilip.tm.user.exception.custom.EmailNotFoundException;
import com.jphilip.tm.user.exception.custom.IdNotFoundException;
import com.jphilip.tm.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceHelper  {

    private final UserRepository userRepository;

    public User validateUserById(Long id){
        return userRepository.findById(id)
                .orElseThrow(() -> new IdNotFoundException(id.toString()));
    }

    public User validateUserByEmail(String email){
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new EmailNotFoundException(email));
    }
}
