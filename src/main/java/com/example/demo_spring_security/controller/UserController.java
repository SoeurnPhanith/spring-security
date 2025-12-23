package com.example.demo_spring_security.controller;

import com.example.demo_spring_security.entity.Users;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/admin/usersAccount")
public class UserController {

    List<Users> usersList = new ArrayList<>(
            List.of(
                    new Users(1, "Soeurn Phanith", "admin@3424"),
                    new Users(2, "Soeurn Channen", "fgni3@fm3")
            )
    );

    @GetMapping
    public ResponseEntity<List<Users>> getAllUsers(){
        return ResponseEntity.ok().body(usersList);
    }

}
