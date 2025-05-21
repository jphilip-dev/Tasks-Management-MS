package com.jphilip.tm.user.controller;

import com.jphilip.tm.user.dto.UserRequestDTO;
import com.jphilip.tm.user.dto.UserResponseDTO;
import com.jphilip.tm.user.service.*;
import com.jphilip.tm.user.service.common.command.dto.CreateUserDTO;
import com.jphilip.tm.user.service.common.command.dto.UpdateUserDTO;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final GetUsersServiceHandler getUsersServiceHandler;
    private final CreateUserService createUserService;
    private final UpdateUserService updateUserService;

    @GetMapping
    public ResponseEntity<List<UserResponseDTO>> getAllUsers(){
        var response = getUsersServiceHandler.getAllUsers();
        return ResponseEntity.ok(response);
    }

    @GetMapping("/user-email/{email}")
    public ResponseEntity<UserResponseDTO> getUserByEmail(@PathVariable String email){
        var response = getUsersServiceHandler.getUserByEmail(email);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponseDTO> getUserById(@PathVariable Long id){
        var response = getUsersServiceHandler.getUserById(id);
        return ResponseEntity.ok(response);
    }

    @PostMapping
    public ResponseEntity<UserResponseDTO> createUser(@Valid @RequestBody UserRequestDTO userRequestDTO, BindingResult bindingResult){

        var response = createUserService.execute(new CreateUserDTO(userRequestDTO, bindingResult));

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/id/{id}")
                .buildAndExpand(response.getId())
                .toUri();

        return ResponseEntity.created(location).body(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserResponseDTO> updateUserById(@PathVariable Long id,@Valid @RequestBody UserRequestDTO userRequestDTO, BindingResult bindingResult){
        var response = updateUserService.execute(new UpdateUserDTO(id, userRequestDTO, bindingResult));
        return ResponseEntity.accepted().body(response);
    }

}
