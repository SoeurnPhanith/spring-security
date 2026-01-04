package com.example.Spring_Security_OAuth2.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

    @GetMapping
    public String greeting(){
        return "Hello Spring OAuth2";
    }

}
