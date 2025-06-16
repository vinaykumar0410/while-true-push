package com.vinay.blog_app.controller;

import com.vinay.blog_app.dto.AuthRequestDTO;
import com.vinay.blog_app.dto.AuthResponseDTO;
import com.vinay.blog_app.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/signup")
    public ResponseEntity<AuthResponseDTO> signUp(
            @RequestBody AuthRequestDTO authRequestDTO
            ){
        return new ResponseEntity<>(authService.signUp(authRequestDTO), HttpStatus.CREATED);
    }

    @PostMapping("/signin")
    public ResponseEntity<String> signIn(
            @RequestBody AuthRequestDTO authRequestDTO
            ){
        return new ResponseEntity<>(authService.signIn(authRequestDTO), HttpStatus.CREATED);
    }

}
