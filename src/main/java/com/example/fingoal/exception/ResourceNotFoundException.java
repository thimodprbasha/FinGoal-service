package com.example.fingoal.exception;

import lombok.Getter;
import org.springframework.http.HttpStatusCode;
@Getter
public class ResourceNotFoundException extends RuntimeException {

    private final HttpStatusCode status;

    public ResourceNotFoundException(String message, HttpStatusCode status) {
        super(message);
        this.status = status;
    }

    public ResourceNotFoundException(String message, Throwable cause, HttpStatusCode status) {
        super(message, cause);
        this.status = status;
    }
}
