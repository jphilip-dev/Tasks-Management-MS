package com.jphilip.tm.user.service;

import com.jphilip.tm.user.dto.UserResponseDTO;
import com.jphilip.tm.user.mapper.UserMapper;
import com.jphilip.tm.user.repository.UserRepository;
import com.jphilip.tm.user.service.util.query.Query;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GetUserByIdService implements Query<Long, UserResponseDTO> {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Override
    public UserResponseDTO execute(Long id) {
        var user = userRepository.findById(id).orElseThrow();
        return userMapper.toDto(user);
    }
}