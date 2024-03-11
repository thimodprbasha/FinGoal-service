package com.example.fingoal.config;

import com.example.fingoal.exception.ResourceNotFoundException;
import com.example.fingoal.utils.Utils;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.List;
import java.util.Map;

@RestControllerAdvice
public class CustomExceptionHandler extends ResponseEntityExceptionHandler  {

    @Override
    public ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        List<String> errors = ex.getBindingResult().getFieldErrors()
                .stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .toList();
        Map<String , Object> responseBody = Utils.ResponseBody(status , errors);
        return new ResponseEntity<>(responseBody, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({ResourceNotFoundException.class})
    public ResponseEntity<Object> handleResourceNotFound(ResourceNotFoundException exception) {
        Map<String , Object> responseBody = Utils.ResponseBody(exception.getStatus() , exception.getMessage());
        return new ResponseEntity<>(responseBody, exception.getStatus());
    }


    @ExceptionHandler({UsernameNotFoundException.class})
    public ResponseEntity<Object> RuntimeException(UsernameNotFoundException exception){
        Map<String , Object> responseBody = Utils.ResponseBody(HttpStatus.FORBIDDEN , exception.getMessage());
        return new ResponseEntity<>(responseBody, HttpStatus.FORBIDDEN);

    }

}
