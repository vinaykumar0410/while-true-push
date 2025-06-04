package com.vinay.blog_app.dto;

import com.vinay.blog_app.model.Post;
import jakarta.persistence.PrePersist;

import java.time.LocalDateTime;
import java.util.List;

public class UserResponseDTO {

    private Long id;
    private String name;
    private String email;
    private String profileImageUrl;
    private List<Post> posts;
    private LocalDateTime createdAt;

    @PrePersist
    public void setCreatedAt(){
        this.createdAt = LocalDateTime.now();
    }

    public UserResponseDTO() {
    }

    public UserResponseDTO(String name, String email, String profileImageUrl, List<Post> posts) {
        this.name = name;
        this.email = email;
        this.profileImageUrl = profileImageUrl;
        this.posts = posts;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getProfileImageUrl() {
        return profileImageUrl;
    }

    public void setProfileImageUrl(String profileImageUrl) {
        this.profileImageUrl = profileImageUrl;
    }

    public List<Post> getPosts() {
        return posts;
    }

    public void setPosts(List<Post> posts) {
        this.posts = posts;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
