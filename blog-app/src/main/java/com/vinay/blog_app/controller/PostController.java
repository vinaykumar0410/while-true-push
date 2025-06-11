package com.vinay.blog_app.controller;

import com.vinay.blog_app.dto.PostRequestDTO;
import com.vinay.blog_app.dto.PostResponseDTO;
import com.vinay.blog_app.dto.UserResponseDTO;
import com.vinay.blog_app.model.User;
import com.vinay.blog_app.security.JwtProvider;
import com.vinay.blog_app.service.PostService;
import com.vinay.blog_app.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/users/user/posts")
public class PostController {

    @Autowired
    private PostService postService;

    @Autowired
    private UserService userService;

    @Autowired
    private ModelMapper modelMapper;

    @PostMapping("/post")
    public ResponseEntity<PostResponseDTO> createPost(
            @RequestBody PostRequestDTO postRequestDTO,
            @RequestHeader("Authorization") String authHeader
            ){
        UserResponseDTO userResponseDTO = userService.findUserByJwtToken(authHeader.substring(7));
        User user = modelMapper.map(userResponseDTO, User.class);
        return new ResponseEntity<>(postService.createPost(
                user.getId(),
                postRequestDTO), HttpStatus.CREATED);
    }



}
