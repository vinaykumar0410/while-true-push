package com.vinay.blog_app.repository;

import com.vinay.blog_app.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {

}
