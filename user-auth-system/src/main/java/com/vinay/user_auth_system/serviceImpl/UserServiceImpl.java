package com.vinay.user_auth_system.serviceImpl;

import com.vinay.user_auth_system.dto.UserRequestDTO;
import com.vinay.user_auth_system.dto.UserResponseDTO;
import com.vinay.user_auth_system.model.User;
import com.vinay.user_auth_system.repository.UserRepository;
import com.vinay.user_auth_system.security.JwtProvider;
import com.vinay.user_auth_system.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private JwtProvider jwtProvider;

    @Override
    public UserResponseDTO signUp(UserRequestDTO userRequestDTO) {
        userRequestDTO.setPassword(passwordEncoder.encode(userRequestDTO.getPassword()));
        User user = modelMapper.map(userRequestDTO, User.class);
        User savedUser = userRepository.save(user);
        return modelMapper.map(savedUser, UserResponseDTO.class);
    }

    @Override
    public String signIn(UserRequestDTO userRequestDTO) {
        Authentication auth = new UsernamePasswordAuthenticationToken(
                userRequestDTO.getEmail(),
                userRequestDTO.getPassword(),
                Collections.singleton(new SimpleGrantedAuthority("ROLE_USER"))
        );
        SecurityContextHolder.getContext().setAuthentication(auth);
        return jwtProvider.generateToken(auth);
    }
}
