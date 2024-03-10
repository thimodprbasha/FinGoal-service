package com.example.fingoal.config;

import com.example.fingoal.exception.ResourceNotFoundException;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@RestControllerAdvice
public class CustomExceptionHandler extends ResponseEntityExceptionHandler  {

    private Map<String , Object> ResponseBody(HttpStatusCode status , String message){
        Map<String, Object> responseBody = new LinkedHashMap<>();
        responseBody.put("timestamp", new Date());
        responseBody.put("status", status.value());
        responseBody.put("error", message);

        return responseBody;
    }

    private <E> Map<String , Object> ResponseBody(HttpStatusCode status , List<E> messages){
        Map<String, Object> responseBody = new LinkedHashMap<>();
        responseBody.put("timestamp", new Date());
        responseBody.put("status", status.value());
        responseBody.put("error", messages);

        return responseBody;
    }
    @Override
    public ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        List<String> errors = ex.getBindingResult().getFieldErrors()
                .stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .toList();
        Map<String , Object> responseBody = ResponseBody(status , errors);
        return new ResponseEntity<>(responseBody, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({ResourceNotFoundException.class})
    public ResponseEntity<Object> handleResourceNotFound(ResourceNotFoundException exception) {
        Map<String , Object> responseBody = ResponseBody(exception.getStatus() , exception.getMessage());
        return new ResponseEntity<>(responseBody, exception.getStatus());
    }


//    @ExceptionHandler({RuntimeException.class})
//    public ResponseEntity<Object> RuntimeException(RuntimeException exception , WebRequest webRequest){
//        Map<String, Object> responseBody = new LinkedHashMap<>();
//        responseBody.put("timestamp", new Date());
//        responseBody.put("status", 403);
//        responseBody.put("errors", "Invalid Username or UserID");
//
//        return new ResponseEntity<>(responseBody, HttpStatus.FORBIDDEN);
//
//    }

}
