package com.SpringBootCourse.springbootcourse.Controller;


import com.SpringBootCourse.springbootcourse.Entity.JournalEntry;
import com.SpringBootCourse.springbootcourse.Entity.User;
import com.SpringBootCourse.springbootcourse.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private AdminService adminService;

    @PostMapping("/add-admin")
    public ResponseEntity<?> addAdmin(@RequestBody User user) {
        return adminService.addAdmin(user);
    }

    @GetMapping("/get-all-users")
    public List<User> getAllUsers() {
     return adminService.getAllUsers();
    }

}
