package com.dating_app.user_service.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.dating_app.user_service.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
    // Custom method to find a user by username
    User findByUsername(String username);
}
