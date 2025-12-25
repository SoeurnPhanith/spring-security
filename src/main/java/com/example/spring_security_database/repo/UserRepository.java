package com.example.spring_security_database.repo;

import com.example.spring_security_database.model.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserModel, Integer> {

    Optional<UserModel> findByEmail(String email);

    boolean existsByEmail(String email);

}
