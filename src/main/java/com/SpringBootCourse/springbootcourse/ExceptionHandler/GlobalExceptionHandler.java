package com.SpringBootCourse.springbootcourse.ExceptionHandler;


import com.SpringBootCourse.springbootcourse.ErrorResponse.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.naming.AuthenticationException;
import java.time.LocalDateTime;

@ControllerAdvice
public class GlobalExceptionHandler {



@ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<ErrorResponse> authenticationError(BadCredentialsException ex){
        ErrorResponse errorResponse = new ErrorResponse("Kuch to galat bheja hai tumney", HttpStatus.UNAUTHORIZED, LocalDateTime.now());
        return new ResponseEntity<>(errorResponse,errorResponse.getHttpStatus());

}



}
