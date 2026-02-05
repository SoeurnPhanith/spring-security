package com.example.spring_reset_password_OTP.repositories;

import com.example.spring_reset_password_OTP.entities.ForgotPasswordEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
public interface ForgotPasswordRepository extends JpaRepository<ForgotPasswordEntity,Long> {
    Optional<ForgotPasswordEntity> findByEmailAndCode(String email,String code);

    @Transactional
    void deleteByEmail(String email);
}
