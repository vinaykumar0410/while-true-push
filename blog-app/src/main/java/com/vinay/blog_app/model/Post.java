package com.vinay.blog_app.model;

import jakarta.persistence.*;
import org.hibernate.mapping.ToOne;

import java.time.LocalDateTime;


@Entity
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String content;
    @ManyToOne
    private Category category;
    @ManyToOne
    private User user;
    private LocalDateTime createdAt;

    public void setCreatedAt(){
        this.createdAt = LocalDateTime.now();
    }

    public Post() {
    }

    public Post(User user, Category category, String content, String title) {
        this.user = user;
        this.category = category;
        this.content = content;
        this.title = title;
    }
}
