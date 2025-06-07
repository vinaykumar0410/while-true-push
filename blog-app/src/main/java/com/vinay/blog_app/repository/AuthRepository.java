package com.vinay.blog_app.repository;

import com.vinay.blog_app.model.AuthUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthRepository extends JpaRepository<AuthUser, Long> {

    AuthUser getUserByEmail(String email);

}
