package com.SpringBootCourse.springbootcourse.service;


import com.SpringBootCourse.springbootcourse.repository.UserRepo;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;


@SpringBootTest
public class UserServiceTest {



    @ParameterizedTest
    @ValueSource(strings = {

    })



    @Disabled
    @Test
    public void testNum(){
        assertEquals(4,2+2);
    }

}
