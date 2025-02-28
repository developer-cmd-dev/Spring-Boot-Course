package com.SpringBootCourse.springbootcourse.Repository;


import com.SpringBootCourse.springbootcourse.repository.UserRepositoryImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class UserRepositoryTest {

    @Autowired
    UserRepositoryImpl userRepository;


    @Disabled
    @Test
    void testSavedNewUser(){
        Assertions.assertNotNull(userRepository.getUserForSA());
    }

}
