package com.example.spring_reset_password_OTP.controller;

import com.example.spring_reset_password_OTP.entities.UserEntity;
import com.example.spring_reset_password_OTP.services.user.UserService;
import lombok.Getter;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/getUser")
    public ResponseEntity<List<UserEntity>> checkAllUser(){
        return userService.checkAllUser();
    }

}
