package com.SpringBootCourse.springbootcourse.service;


import com.SpringBootCourse.springbootcourse.Entity.User;
import com.SpringBootCourse.springbootcourse.repository.UserRepo;
import org.bson.types.ObjectId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserRepo userRepo;

    private static final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    private static final Logger logger = LoggerFactory.getLogger(UserService.class);

    public List<User> getAllUser(){
        return userRepo.findAll();
    }

    public boolean saveNewUser(User user){
     try{
         user.setPassword(passwordEncoder.encode(user.getPassword()));
         user.setRoles(List.of("USER"));
         userRepo.save(user);
         return true;
     }catch (Exception e){
         logger.error("hahahha");
         return false;
     }
    }

    public User saveNewAdmin(User user){
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRoles(List.of("USER","ADMIN"));
        return userRepo.save(user);
    }

    public void saveUser(User user){
        userRepo.save(user);
    }

    public void updateUser(User user){
        Authentication authentication= SecurityContextHolder.getContext().getAuthentication();
        String userName = authentication.getName();
        if(userName !=null){
            User userData = userRepo.findByUsername(userName);
            saveUser(userData);
        }

    }

    public void deleteUser(){
        Authentication authentication= SecurityContextHolder.getContext().getAuthentication();
        String userName = authentication.getName();
        if(userName !=null){
            userRepo.deleteByUsername(userName);
        }
    }

    public User getUserByUsername(String username){
       return userRepo.findByUsername(username);
    }

    public User getUserById(ObjectId id){
        return userRepo.findById(id).orElse(null);
    }











}
