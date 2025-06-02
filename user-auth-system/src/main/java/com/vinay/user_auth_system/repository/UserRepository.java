package com.vinay.user_auth_system.repository;

import com.vinay.user_auth_system.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

    public User getUserByEmail(String email);

}
