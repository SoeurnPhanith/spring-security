package com.example.spring_security_database.controller;

import com.example.spring_security_database.model.UserModel;
import com.example.spring_security_database.service.UserRegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class UserAuthController {

    @Autowired
    private UserRegisterService registerService;

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody UserModel user) {
        return registerService.register(user);
    }
}
