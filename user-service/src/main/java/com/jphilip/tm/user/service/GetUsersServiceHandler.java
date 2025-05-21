package com.jphilip.tm.user.service;

import com.jphilip.tm.user.dto.UserResponseDTO;
import com.jphilip.tm.user.exception.custom.EmailNotFoundException;
import com.jphilip.tm.user.exception.custom.IdNotFoundException;
import com.jphilip.tm.user.mapper.UserMapper;
import com.jphilip.tm.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GetUsersServiceHandler {

    private final UserRepository userRepository;
    private final UserServiceHelper userServiceHelper;
    private final UserMapper userMapper;

    public List<UserResponseDTO> getAllUsers() {
        return userRepository.findAll().stream().map(userMapper::toDto).toList();
    }

    public UserResponseDTO getUserByEmail(String email) {
        var user = userServiceHelper.validateUserByEmail(email);
        return userMapper.toDto(user);
    }

    public UserResponseDTO getUserById(Long id) {
        var user = userServiceHelper.validateUserById(id);
        return userMapper.toDto(user);
    }
}
