package com.SpringBootCourse.springbootcourse.Controller;


import com.SpringBootCourse.springbootcourse.Entity.JournalEntry;
import com.SpringBootCourse.springbootcourse.Entity.User;
import com.SpringBootCourse.springbootcourse.service.UserService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;


    @GetMapping
    public List<User> getUser(){
        return userService.getAllUser();
    }


    @PostMapping
    public ResponseEntity<?> addUser(@RequestBody  User user){
        try {
            userService.saveUser(user);
            return new ResponseEntity<>(HttpStatus.OK);

        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/{username}")
    public  ResponseEntity<?> getAll(@PathVariable String username){
        User data = userService.getUserByUsername(username);
        if(data!=null){
            return new ResponseEntity<>(data,HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);

    }

    @GetMapping("/id/{id}")
    public ResponseEntity<?> getUserById(@PathVariable ObjectId id){
        try{
            User responseData = userService.getUserById(id);
            return new ResponseEntity<User>(responseData,HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }




}
