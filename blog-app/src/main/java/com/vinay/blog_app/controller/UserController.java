package com.vinay.blog_app.controller;

import com.vinay.blog_app.dto.AuthRequestDTO;
import com.vinay.blog_app.dto.AuthResponseDTO;
import com.vinay.blog_app.dto.UserRequestDTO;
import com.vinay.blog_app.dto.UserResponseDTO;
import com.vinay.blog_app.model.User;
import com.vinay.blog_app.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/user")
    public ResponseEntity<UserResponseDTO> createUser(
            @RequestBody UserRequestDTO userRequestDTO
            ){
        return new ResponseEntity<>(userService.createUser(userRequestDTO), HttpStatus.CREATED);
    }



}
