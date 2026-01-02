package com.example.spring_security_JWT.service;

import com.example.spring_security_JWT.model.UserModel;
import com.example.spring_security_JWT.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository useRepo;

    @Autowired
    private PasswordEncoder encoder;

    @Autowired
    private AuthenticationManager authManager;

    @Autowired
    private JwtService jwtService;

    public UserModel createAccount(UserModel user){
        user.setPassword(encoder.encode(user.getPassword()));
        UserModel register = useRepo.save(user);
        return register;
    }

    //verify when login
    public String verify(UserModel user) {
        Authentication authentication =
                authManager.authenticate(new UsernamePasswordAuthenticationToken(
                        user.getEmail(),
                        user.getPassword()
                ));

        //check
        if(authentication.isAuthenticated()){
            return jwtService.generateToken(user.getEmail());
        }
        return "Fail";
    }
}
