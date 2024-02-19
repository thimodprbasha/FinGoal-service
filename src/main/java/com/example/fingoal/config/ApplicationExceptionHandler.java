package com.example.fingoal.config;

import com.example.fingoal.exception.UserNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;

//@RestControllerAdvice
public class ApplicationExceptionHandler {
//    @ExceptionHandler({UsernameNotFoundException.class})
//    public ResponseEntity<Object> UserNotFoundException(UsernameNotFoundException exception , WebRequest webRequest){
//        Map<String, Object> responseBody = new LinkedHashMap<>();
//        responseBody.put("timestamp", new Date());
//        responseBody.put("status", 403);
//        responseBody.put("errors", "Invalid Username or UserID");
//
//        return new ResponseEntity<>(responseBody, HttpStatus.FORBIDDEN);
//
//    }

//    @ExceptionHandler(MethodArgumentNotValidException.class)
}
