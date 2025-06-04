package com.vinay.blog_app.serviceimpl;

import com.vinay.blog_app.dto.AuthRequestDTO;
import com.vinay.blog_app.dto.AuthResponseDTO;
import com.vinay.blog_app.model.AuthUser;
import com.vinay.blog_app.repository.AuthRepository;
import com.vinay.blog_app.security.JwtProvider;
import com.vinay.blog_app.service.AuthService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class AuthServiceImpl implements AuthService {

    @Autowired
    private AuthRepository authRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private JwtProvider jwtProvider;

    @Override
    public AuthResponseDTO signUp(AuthRequestDTO authRequestDTO) {
        authRequestDTO.setPassword(passwordEncoder.encode(authRequestDTO.getPassword()));
        AuthUser user = modelMapper.map(authRequestDTO, AuthUser.class);
        AuthUser savedUser = authRepository.save(user);
        return modelMapper.map(savedUser, AuthResponseDTO.class);
    }

    @Override
    public String signIn(AuthRequestDTO authRequestDTO) {
        Authentication auth = new UsernamePasswordAuthenticationToken(
                authRequestDTO.getEmail(),
                authRequestDTO.getPassword(),
                Collections.singleton(new SimpleGrantedAuthority("ROLE_USER"))
        );
        SecurityContextHolder.getContext().setAuthentication(auth);
        return jwtProvider.generateToken(auth);
    }
}
