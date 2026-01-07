package com.example.Spring_Security_OAuth2.repo;

import com.example.Spring_Security_OAuth2.model.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserModel, Long> {
    Optional<UserModel> findByEmail(String email);
}

