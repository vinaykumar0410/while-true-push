package com.vinay.blog_app.serviceimpl;

import com.vinay.blog_app.Exception.UserNotFoundException;
import com.vinay.blog_app.dto.UserRequestDTO;
import com.vinay.blog_app.dto.UserResponseDTO;
import com.vinay.blog_app.model.User;
import com.vinay.blog_app.repository.UserRepository;
import com.vinay.blog_app.security.JwtProvider;
import com.vinay.blog_app.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JwtProvider jwtProvider;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public UserResponseDTO createUser(UserRequestDTO userRequestDTO) {
        User user = modelMapper.map(userRequestDTO, User.class);
        User savedUser = userRepository.save(user);
        return modelMapper.map(savedUser, UserResponseDTO.class);
    }

    @Override
    public UserResponseDTO updateUser(Long userId, UserRequestDTO dto) {
        User existingUser = userRepository.findById(userId).orElseThrow(
                () -> new UserNotFoundException("User Not Found With Id : " + userId)
        );
        existingUser.setName(existingUser.getName());
        existingUser.setEmail(existingUser.getEmail());
        existingUser.setProfileImageUrl(existingUser.getProfileImageUrl());
        User savedUser = userRepository.save(existingUser);
        return modelMapper.map(savedUser, UserResponseDTO.class);
    }

    @Override
    public void deleteUser(Long userId) {
        User existingUser = userRepository.findById(userId).orElseThrow(
                () -> new UserNotFoundException("User Not Found With Id : " + userId)
        );
        userRepository.delete(existingUser);
    }

    @Override
    public UserResponseDTO getUserById(Long userId) {
        User existingUser = userRepository.findById(userId).orElseThrow(
                () -> new UserNotFoundException("User Not Found With Id : " + userId)
        );
        return modelMapper.map(existingUser, UserResponseDTO.class);
    }

    @Override
    public List<UserResponseDTO> getAllUsers() {
        List<User> users = userRepository.findAll();
        return users
                .stream()
                .map(user -> modelMapper.map(user, UserResponseDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public UserResponseDTO findUserByJwtToken(String jwt) {
        String email = jwtProvider.getEmailFromToken(jwt);
        User user = userRepository.findByEmail(email);
        return modelMapper.map(user, UserResponseDTO.class);
    }

}
