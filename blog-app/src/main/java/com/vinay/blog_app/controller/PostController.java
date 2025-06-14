package com.vinay.blog_app.controller;

import com.vinay.blog_app.dto.PostRequestDTO;
import com.vinay.blog_app.dto.PostResponseDTO;
import com.vinay.blog_app.dto.UserResponseDTO;
import com.vinay.blog_app.model.Post;
import com.vinay.blog_app.model.User;
import com.vinay.blog_app.security.JwtProvider;
import com.vinay.blog_app.service.PostService;
import com.vinay.blog_app.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/posts")
public class PostController {

    @Autowired
    private PostService postService;

    @Autowired
    private UserService userService;

    @Autowired
    private ModelMapper modelMapper;

    @PostMapping
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

    @PostMapping("/{postId}")
    public ResponseEntity<PostResponseDTO> updatePost(
        @RequestBody PostRequestDTO postRequestDTO,
        @PathVariable("postId") Long postId,
        @RequestHeader("Authorization") String authHeader
    ){
        UserResponseDTO userResponseDTO = userService.findUserByJwtToken(authHeader);
        User existingUser = modelMapper.map(userResponseDTO, User.class);
        PostResponseDTO postResponseDTO = postService.getPostById(postId);
        Post existingPost = modelMapper.map(postResponseDTO, Post.class);
        return new ResponseEntity<>(
          postService.updatePost(existingUser.getId(), existingPost.getId(), postRequestDTO),
                HttpStatus.OK
        );
    }

    @DeleteMapping("/{postId}")
    public void deletePost(
            @PathVariable("postId") Long postId,
            @RequestHeader("Authorization") String authHeader
    ){
        UserResponseDTO userResponseDTO = userService.findUserByJwtToken(authHeader);
        User existingUser = modelMapper.map(userResponseDTO, User.class);
        postService.deletePost(existingUser.getId(), postId);
    }

    @GetMapping
    public ResponseEntity<List<PostResponseDTO>> getAllPosts(){
        return new ResponseEntity<>(postService.getAllPosts(), HttpStatus.OK);
    }

    @GetMapping("/search/{keyword}")
    public ResponseEntity<List<PostResponseDTO>> searchPosts(
            @RequestParam("keyword") String keyword
    ){
        return new ResponseEntity<>(postService.searchPosts(keyword), HttpStatus.OK);
    }

    @GetMapping("/{categoryId}")
    public ResponseEntity<List<PostResponseDTO>> getPostsByCategory(
            @PathVariable("categoryId") Long categoryId
    ){
        return new ResponseEntity<>(postService.getPostsByCategory(categoryId), HttpStatus.OK);
    }

      @GetMapping("/{userId}")
    public ResponseEntity<List<PostResponseDTO>> getPostsByUser(
            @PathVariable("userId") Long userId
    ){
        return new ResponseEntity<>(postService.getPostsByUser(userId), HttpStatus.OK);
    }

}

/*

    PostResponseDTO createPost(Long userId, PostRequestDTO postRequestDTO);
    PostResponseDTO updatePost(Long userId, Long postId, PostRequestDTO postRequestDTO);
    void deletePost(Long userId, Long postId); PostResponseDTO getPostById(Long postId);
    List<PostResponseDTO> getAllPosts();
    
    List<PostResponseDTO> searchPosts(String keyword);
    List<PostResponseDTO> getPostsByCategory(Long categoryId);
    List<PostResponseDTO> getPostsByUser(Long userId);

 */