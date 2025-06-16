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
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @PostMapping("/user/{userId}")
    public ResponseEntity<UserResponseDTO> updateUser(
            @PathVariable("userId") Long userId,
            @RequestBody UserRequestDTO userRequestDTO
    ){
        return new ResponseEntity<>(userService.updateUser(userId, userRequestDTO), HttpStatus.OK);
    }

    @DeleteMapping("user/{userId}")
    public void deleteUser(
            @PathVariable("userId") Long userId
    ){
        userService.deleteUser(userId);
    }

    @GetMapping("user/{userId}")
    public ResponseEntity<UserResponseDTO> getUserById(
            @PathVariable("userId") Long userId
    ){
        return new ResponseEntity<>(userService.getUserById(userId), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<UserResponseDTO>> getUsers(){
        return new ResponseEntity<>(userService.getAllUsers(), HttpStatus.OK);
    }

}

/*

        UserResponseDTO createUser(UserRequestDTO dto);
        UserResponseDTO updateUser(Long userId, UserRequestDTO dto);
        void deleteUser(Long userId);
        UserResponseDTO getUserById(Long userId);
        List<UserResponseDTO> getAllUsers();
        UserResponseDTO findUserByJwtToken(String jwt);

 */