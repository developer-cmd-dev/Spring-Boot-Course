package com.SpringBootCourse.springbootcourse.service;


import com.SpringBootCourse.springbootcourse.Entity.User;
import com.SpringBootCourse.springbootcourse.repository.UserRepo;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserService {
    @Autowired
    private UserRepo userRepo;

    public List<User> getAllUser(){
        return userRepo.findAll();
    }

    public void saveUser(User user){
        userRepo.save(user);
    }

    public User getUserByUsername(String username){
       return userRepo.findByUsername(username);
    }

    public User getUserById(ObjectId id){
        return userRepo.findById(id).orElse(null);
    }











}
