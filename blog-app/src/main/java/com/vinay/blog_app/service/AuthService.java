package com.vinay.blog_app.service;

import com.vinay.blog_app.dto.AuthRequestDTO;
import com.vinay.blog_app.dto.AuthResponseDTO;

public interface AuthService {

    public AuthResponseDTO signUp(AuthRequestDTO authRequestDTO);

    public String signIn(AuthRequestDTO authRequestDTO);

}
