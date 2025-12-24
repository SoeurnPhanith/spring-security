package com.example.spring_security_inRAM_part2.controller;

import com.example.spring_security_inRAM_part2.model.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping ("/api/auth/admin")
public class UserController {

    //List of User Clas
    List<Users> usersList = new ArrayList<>();

    @GetMapping
    public ResponseEntity<List<Users>> showAllUser(){
        if(usersList.isEmpty())
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();

        return ResponseEntity.ok().body(usersList);
    }

    // method to add user from register controller
    public void addUserToList(Users user){
        usersList.add(user);
    }

}