package com.example.spring_reset_password_OTP.controller;

import com.example.spring_reset_password_OTP.entities.UserEntity;
import com.example.spring_reset_password_OTP.services.user.UserAuthService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserAuthController {

    private final UserAuthService userAuthService;

    public UserAuthController(UserAuthService userRegisterService) {
        this.userAuthService = userRegisterService;
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody UserEntity user) {
        return userAuthService.register(user);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(
            @RequestParam String email,
            @RequestParam String password,
            HttpServletRequest request
    ) {
        userAuthService.login(email, password, request);
        return ResponseEntity.ok("Login successful");
    }
}
