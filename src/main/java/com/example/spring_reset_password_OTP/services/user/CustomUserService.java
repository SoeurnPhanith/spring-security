package com.example.spring_reset_password_OTP.services.user;

import com.example.spring_reset_password_OTP.entities.UserEntity;
import com.example.spring_reset_password_OTP.repositories.UserRepository;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserService implements UserDetailsService {

    private final UserRepository userRepository;

    public CustomUserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        UserEntity user = userRepository.findByEmail(email)
                .orElseThrow(()->new RuntimeException("user not found"));

        return User.builder()
                .username(user.getEmail())
                .password(user.getPassword())
                .build();
    }
}
