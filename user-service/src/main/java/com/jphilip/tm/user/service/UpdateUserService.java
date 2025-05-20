package com.jphilip.tm.user.service;

import com.jphilip.tm.user.dto.UserResponseDTO;
import com.jphilip.tm.user.exception.custom.EmailMismatchException;
import com.jphilip.tm.user.exception.custom.FieldErrorException;
import com.jphilip.tm.user.exception.custom.IdNotFoundException;
import com.jphilip.tm.user.mapper.UserMapper;
import com.jphilip.tm.user.repository.UserRepository;
import com.jphilip.tm.user.service.util.command.Command;
import com.jphilip.tm.user.service.util.command.dto.UpdateUserDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UpdateUserService implements Command<UpdateUserDTO, UserResponseDTO> {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserMapper userMapper;


    @Override
    public UserResponseDTO execute(UpdateUserDTO updateUserDTO) {

        // Extract Data
        var id = updateUserDTO.id();
        var userRequestDTO = updateUserDTO.userRequestDTO();
        var bindingResult = updateUserDTO.bindingResult();

        // To maintain uniformity as other service use this exception instead of MethodArgumentValEx....
        if (bindingResult.hasErrors()){
            throw new FieldErrorException(bindingResult);
        }

        // Retrieve existing user
        var user = userRepository.findById(id)
                .orElseThrow(() -> new IdNotFoundException(id.toString()));

        // Check existing and update request email is matching NOTE: EMAIL is final
        if (!user.getEmail().equals(userRequestDTO.getEmail())){
            throw new EmailMismatchException();
        }

        // check ownership once auth service is ok


        // Update user fields base on the userRequestDTO
        user.setName(userRequestDTO.getName());
        user.setPassword( passwordEncoder.encode(userRequestDTO.getPassword()) );
        userRepository.save(user);

        return userMapper.toDto(user);
    }
}
