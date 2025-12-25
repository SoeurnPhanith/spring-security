package com.example.spring_security_database.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth/admin")
public class UserController {

    @GetMapping
    public String greeting() {
        return "Hello, Admin";
    }
}
