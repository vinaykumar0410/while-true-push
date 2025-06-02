package com.vinay.user_auth_system.service;

import com.vinay.user_auth_system.dto.UserRequestDTO;
import com.vinay.user_auth_system.dto.UserResponseDTO;

public interface UserService {

    public UserResponseDTO signUp(UserRequestDTO userRequestDTO);

    public String signIn(UserRequestDTO userRequestDTO);

}
