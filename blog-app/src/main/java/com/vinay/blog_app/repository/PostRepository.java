package com.vinay.blog_app.repository;

import com.vinay.blog_app.model.Category;
import com.vinay.blog_app.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {

    public List<Post> findByCategoryId(Long categoryId);

    List<Post> findByUserId(Long userId);

    @Query("SELECT p FROM Post p WHERE p.title LIKE %:keyword% OR p.content LIKE %:keyword% OR p.category LIKE %:keyword%")
    List<Post> searchPost(@Param("keyword") String keyword);

}
