package com.jphilip.tm.user.service;

import com.jphilip.tm.user.dto.UserResponseDTO;
import com.jphilip.tm.user.mapper.UserMapper;
import com.jphilip.tm.user.repository.UserRepository;
import com.jphilip.tm.user.service.util.query.Query;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GetAllUsersService implements Query<Void, List<UserResponseDTO>> {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Override
    public List<UserResponseDTO> execute(Void input) {
        return userRepository.findAll().stream().map(userMapper::toDto).toList();
    }
}
