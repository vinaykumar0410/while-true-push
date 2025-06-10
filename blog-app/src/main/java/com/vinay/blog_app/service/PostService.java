package com.vinay.blog_app.service;

import com.vinay.blog_app.dto.PostRequestDTO;
import com.vinay.blog_app.dto.PostResponseDTO;

import java.util.List;

public interface PostService {

    PostResponseDTO createPost(Long userId, PostRequestDTO postRequestDTO);
    PostResponseDTO updatePost(Long userId, Long postId, PostRequestDTO postRequestDTO);
    void deletePost(Long userId, Long postId); PostResponseDTO getPostById(Long postId);
    List<PostResponseDTO> getAllPosts();
    List<PostResponseDTO> searchPosts(String keyword);
    List<PostResponseDTO> getPostsByCategory(Long categoryId);
    List<PostResponseDTO> getPostsByUser(Long userId);

}
