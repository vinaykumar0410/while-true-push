package com.vinay.blog_app.serviceimpl;

import com.vinay.blog_app.Exception.CategoryNotFoundException;
import com.vinay.blog_app.Exception.PostNotFoundException;
import com.vinay.blog_app.Exception.UserNotFoundException;
import com.vinay.blog_app.dto.PostRequestDTO;
import com.vinay.blog_app.dto.PostResponseDTO;
import com.vinay.blog_app.model.Category;
import com.vinay.blog_app.model.Post;
import com.vinay.blog_app.model.User;
import com.vinay.blog_app.repository.CategoryRepository;
import com.vinay.blog_app.repository.PostRepository;
import com.vinay.blog_app.repository.UserRepository;
import com.vinay.blog_app.service.PostService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.stream.Collectors;

public class PostServiceImpl implements PostService {

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public PostResponseDTO createPost(Long userId, PostRequestDTO postRequestDTO) {
        User existingUser = userRepository.findById(userId).orElseThrow(
                () -> new UserNotFoundException("User Not Found With Id " + userId)
        );
        Post post = modelMapper.map(postRequestDTO, Post.class);
        post.setUser(existingUser);
        Post savedPost = postRepository.save(post);
        existingUser.getPosts().add(post.getId());
        userRepository.save(existingUser);
        return modelMapper.map(post, PostResponseDTO.class);
    }

    @Override
    public PostResponseDTO updatePost(Long userId, Long postId, PostRequestDTO postRequestDTO) {
        Post existingPost = postRepository.findById(postId).orElseThrow(
                () -> new PostNotFoundException("Post Not Found With ID " + postId)
        );
        User existingUser = userRepository.findById(userId).orElseThrow(
                () -> new UserNotFoundException("User Not Found With ID " + userId)
        );
        Category category = categoryRepository.findById(postRequestDTO.getCategoryId()).orElseThrow(
                () -> new CategoryNotFoundException("Category Not Found With ID " + postRequestDTO.getCategoryId())
        );
        existingPost.setTitle(postRequestDTO.getTitle());
        existingPost.setContent(postRequestDTO.getContent());
        existingPost.setCategory(category);
        Post savedPost = postRepository.save(existingPost);
        return modelMapper.map(savedPost, PostResponseDTO.class);
    }

    @Override
    public void deletePost(Long userId, Long postId) {
        Post existingPost = postRepository.findById(postId).orElseThrow(
                () -> new PostNotFoundException("Post Not Found With ID " + postId)
        );
        postRepository.delete(existingPost);
        User existingUser = userRepository.findById(userId).orElseThrow(
                () -> new UserNotFoundException("User Not Found With ID " + userId)
        );
        existingUser.getPosts().remove(existingPost.getId());
        userRepository.save(existingUser);
    }

    @Override
    public PostResponseDTO getPostById(Long postId) {
        Post existingPost = postRepository.findById(postId).orElseThrow(
                () -> new PostNotFoundException("Post Not Found With ID " + postId)
        );
        return modelMapper.map(existingPost, PostResponseDTO.class);
    }

    @Override
    public List<PostResponseDTO> getAllPosts() {
        List<Post> posts = postRepository.findAll();
        return posts
                .stream()
                .map(post -> modelMapper.map(post, PostResponseDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<PostResponseDTO> searchPosts(String keyword) {
        List<Post> posts = postRepository.searchPost(keyword);
        return posts
                .stream()
                .map(post -> modelMapper.map(post, PostResponseDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<PostResponseDTO> getPostsByCategory(Long categoryId) {
        List<Post> posts = postRepository.findByCategoryId(categoryId);
        return posts
                .stream()
                .map(post -> modelMapper.map(post, PostResponseDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<PostResponseDTO> getPostsByUser(Long userId) {
        List<Post> posts = postRepository.findByUserId(userId);
        return posts
                .stream()
                .map(post -> modelMapper.map(post, PostResponseDTO.class))
                .collect(Collectors.toList());
    }
}
