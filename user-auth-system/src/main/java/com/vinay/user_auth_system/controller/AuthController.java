package com.vinay.user_auth_system.controller;

import com.vinay.user_auth_system.dto.UserRequestDTO;
import com.vinay.user_auth_system.dto.UserResponseDTO;
import com.vinay.user_auth_system.repository.UserRepository;
import com.vinay.user_auth_system.serviceImpl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserServiceImpl userService;

    @GetMapping("/protected")
    public String getThis(){
        return "This is Protected Route";
    }

    @PostMapping("/signup")
    public ResponseEntity<UserResponseDTO> signUp(
            @RequestBody UserRequestDTO userRequestDTO
            ){
        return new ResponseEntity<>(userService.signUp(userRequestDTO), HttpStatus.CREATED);
    }
    @PostMapping("/signin")
    public ResponseEntity<String> signIn(
            @RequestBody UserRequestDTO userRequestDTO
            ){
        return new ResponseEntity<>(userService.signIn(userRequestDTO), HttpStatus.CREATED);
    }


}
