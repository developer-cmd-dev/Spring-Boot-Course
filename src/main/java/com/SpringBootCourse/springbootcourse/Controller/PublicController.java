package com.SpringBootCourse.springbootcourse.Controller;

import com.SpringBootCourse.springbootcourse.Entity.User;
import com.SpringBootCourse.springbootcourse.repository.UserRepositoryImpl;
import com.SpringBootCourse.springbootcourse.service.EmailService;
import com.SpringBootCourse.springbootcourse.service.UserService;
import com.SpringBootCourse.springbootcourse.service.WeatherService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.swing.text.html.HTML;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/public")
public class PublicController {
    @Autowired
    private UserService userService;
    @Autowired
    private WeatherService weatherService;
    @Autowired
    private EmailService emailService;

    @GetMapping("send-mail")
    public ResponseEntity<List<User>> getUser(){
        try {
            emailService.sendEmail("devcool932@gmail.com","Testing Java email sender","Hey dev what's up bro");
            return new ResponseEntity<>(HttpStatus.OK);
        }catch (Exception e){
            log.error(e.getLocalizedMessage());
            return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
        }

    }

    @PostMapping
    public ResponseEntity<?> addUser(@RequestBody User user){
        try {
            userService.saveNewUser(user);
            return new ResponseEntity<>(HttpStatus.OK);

        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/get-weather/{city}")
    public void getWeather(@PathVariable String city){
       weatherService.getWeather(city);
    }
}
