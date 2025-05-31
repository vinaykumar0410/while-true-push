package com.vinay.blog_app.service;

import com.vinay.blog_app.dto.UserRequestDTO;
import com.vinay.blog_app.dto.UserResponseDTO;

import java.util.List;

public interface UserService {

    UserResponseDTO createUser(UserRequestDTO dto);
    UserResponseDTO updateUser(Long userId, UserRequestDTO dto);
    void deleteUser(Long userId);
    UserResponseDTO getUserById(Long userId);
    List<UserResponseDTO> getAllUsers();


}
