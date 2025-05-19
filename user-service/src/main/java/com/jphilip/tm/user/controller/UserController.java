package com.jphilip.tm.user.controller;

import com.jphilip.tm.user.dto.UserRequestDTO;
import com.jphilip.tm.user.dto.UserResponseDTO;
import com.jphilip.tm.user.service.*;
import com.jphilip.tm.user.service.util.command.dto.UpdateUserDTO;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final GetAllUsersService getAllUsersService;
    private final GetUserByEmailService getUserByEmailService;
    private final GetUserByIdService getUserByIdService;
    private final CreateUserService createUserService;
    private final UpdateUserService updateUserService;

    @GetMapping
    public ResponseEntity<List<UserResponseDTO>> getAllUsers(){
        var response = getAllUsersService.execute(null);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/email/{email}")
    public ResponseEntity<UserResponseDTO> getUserByEmail(@PathVariable String email){
        var response = getUserByEmailService.execute(email);
        return ResponseEntity.ok(response);
    }
    @GetMapping("/id/{id}")
    public ResponseEntity<UserResponseDTO> getUserById(@PathVariable Long id){
        var response = getUserByIdService.execute(id);
        return ResponseEntity.ok(response);
    }

    @PostMapping
    public ResponseEntity<UserResponseDTO> createUser(@Valid @RequestBody UserRequestDTO userRequestDTO){
        var response = createUserService.execute(userRequestDTO);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/id/{id}")
                .buildAndExpand(response.getId())
                .toUri();

        return ResponseEntity.created(location).body(response);
    }

    @PutMapping("/id/{id}")
    public ResponseEntity<UserResponseDTO> updateUserById(@PathVariable Long id,@Valid @RequestBody UserRequestDTO userRequestDTO){
        var response = updateUserService.execute(new UpdateUserDTO(id, userRequestDTO));
        return ResponseEntity.accepted().body(response);
    }

}
