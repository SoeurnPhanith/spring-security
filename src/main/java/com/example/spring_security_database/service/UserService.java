package com.example.spring_security_database.service;

import com.example.spring_security_database.model.UserModel;
import com.example.spring_security_database.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserController {

    ResponseEntity<List<UserModel>> checkAllUser();

    ResponseEntity<UserModel> checkUserById(Integer id);

    ResponseEntity<UserModel> updateStudent(UserModel userModel, Integer id);

    ResponseEntity<String> removeUser(Integer id);

}
