package com.SpringBootCourse.springbootcourse.service;

import org.junit.jupiter.api.AutoClose;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class EmailServiceTest {

    @Autowired
    private EmailService emailService;



    @Disabled
    @Test
    void sendMail(){
        emailService.sendEmail("devcool932@gmail.com","Testing Java email sender","Hey dev what's up bro");
    }

}
