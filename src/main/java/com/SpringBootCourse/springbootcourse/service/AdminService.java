package com.SpringBootCourse.springbootcourse.service;


import com.SpringBootCourse.springbootcourse.Entity.User;
import com.SpringBootCourse.springbootcourse.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class AdminService {


    @Autowired
    private UserService userService;
    @Autowired
    private UserRepo userRepo;

    public ResponseEntity<?> addAdmin(User user){
        User saved = userService.saveNewAdmin(user);
        return new ResponseEntity<>(saved, HttpStatus.OK);
    }

    public List<User> getAllUsers(){
        return userRepo.findAll();
    }

}
