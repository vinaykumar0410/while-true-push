package com.vinay.blog_app.serviceimpl;

import com.vinay.blog_app.dto.PostRequestDTO;
import com.vinay.blog_app.dto.PostResponseDTO;
import com.vinay.blog_app.repository.PostRepository;
import com.vinay.blog_app.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class PostServiceImpl implements PostService {

    @Autowired
    private PostRepository postRepository;


    @Override
    public PostResponseDTO createPost(Long userId, PostRequestDTO postRequestDTO) {

        return null;
    }

    @Override
    public PostResponseDTO updatePost(Long userId, Long postId, PostRequestDTO postRequestDTO) {
        return null;
    }

    @Override
    public void deletePost(Long userId, Long postId) {

    }

    @Override
    public PostResponseDTO getPostById(Long postId) {
        return null;
    }

    @Override
    public List<PostResponseDTO> getAllPosts() {
        return List.of();
    }

    @Override
    public List<PostResponseDTO> searchPosts(String keyword) {
        return List.of();
    }

    @Override
    public List<PostResponseDTO> getPostsByCategory(Long categoryId) {
        return List.of();
    }

    @Override
    public List<PostResponseDTO> getPostsByUser(Long userId) {
        return List.of();
    }
}
