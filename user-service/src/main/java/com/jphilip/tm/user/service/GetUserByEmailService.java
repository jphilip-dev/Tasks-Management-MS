package com.jphilip.tm.user.service;

import com.jphilip.tm.user.dto.UserResponseDTO;
import com.jphilip.tm.user.mapper.UserMapper;
import com.jphilip.tm.user.repository.UserRepository;
import com.jphilip.tm.user.service.util.query.Query;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GetUserByEmailService implements Query<String, UserResponseDTO> {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Override
    public UserResponseDTO execute(String email) {
        var user = userRepository.findByEmail(email).orElseThrow();
        return userMapper.toDto(user);
    }
}