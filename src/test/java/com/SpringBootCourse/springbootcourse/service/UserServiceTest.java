package com.SpringBootCourse.springbootcourse.service;

import com.SpringBootCourse.springbootcourse.Entity.User;
import com.SpringBootCourse.springbootcourse.repository.UserRepo;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ArgumentsSource;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class UserServiceTest {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private UserService userService;

    @Disabled
    @Test
    public void addTest(){
        assertEquals(4,2+2);
    }



    @Disabled
    @ParameterizedTest
    @ValueSource(strings={
            "devmandal",
            "admin",
            "sunil"
    })
    public void findUsernameTest(String name){
        assertNotNull(userRepo.findByUsername(name));
    }

    @Disabled
    @ParameterizedTest
    @ArgumentsSource(AddUserTestArgument.class)
    public void addUserTest(User user){
        assertTrue(userService.saveNewUser(user));
    }


    @Disabled
    @ParameterizedTest
    @CsvSource({
            "1,1,2",
            "1,2,3",
            "1,2,5",
    })
    public void parametrizedTest(int a,int b, int expected){
        assertEquals(expected,a+b);
    }

}
