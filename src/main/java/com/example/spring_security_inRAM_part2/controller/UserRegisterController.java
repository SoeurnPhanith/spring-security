package com.example.spring_security_inRAM_part2.controller;

import com.example.spring_security_inRAM_part2.model.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class UserRegisterController{

    @Autowired
    private InMemoryUserDetailsManager userDetailsManager;

    // Inject UserController to save in list (or make usersList static/shared)
    @Autowired
    private UserController userController;

    @PostMapping("/register")
    public ResponseEntity<?> registerAccount(@RequestBody Users users){

        if(userDetailsManager.userExists(users.getUsername())){
            return ResponseEntity
                    .status(HttpStatus.CONFLICT)
                    .body("This user already exists");
        }

        UserDetails newUser = User
                .builder()
                .username(users.getUsername())
                .password(users.getPassword()) // PLAIN
                .roles("USER")
                .build();

        userDetailsManager.createUser(newUser);

        //add data register to user list
        userController.addUserToList(users);

        return ResponseEntity.ok(users);
    }
}
