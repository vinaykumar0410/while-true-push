package com.vinay.blog_app.service;

import com.vinay.blog_app.dto.PostRequestDTO;
import com.vinay.blog_app.dto.PostResponseDTO;

import java.util.List;

public interface PostService {

    PostResponseDTO createPost(PostRequestDTO postRequestDTO);
    PostResponseDTO updatePost(Long postId, PostRequestDTO postRequestDTO);
    void deletePost(Long postId);
    PostResponseDTO getPostById(Long postId);
    List<PostResponseDTO> getAllPosts();
    List<PostResponseDTO> searchPosts(String keyword);
    List<PostResponseDTO> getPostsByCategory(Long categoryId);
    List<PostResponseDTO> getPostsByUser(Long userId);


}
