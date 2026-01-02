package com.example.spring_security_JWT.controller;

import com.example.spring_security_JWT.model.UserModel;
import com.example.spring_security_JWT.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class UserController {

    @Autowired
    private UserService service;

    @PostMapping("/register")
    public UserModel register(@RequestBody UserModel user){
        return service.createAccount(user);
    }

    @PostMapping("/login")
    public String login(@RequestBody UserModel user){
        return service.verify(user);
    }

}
