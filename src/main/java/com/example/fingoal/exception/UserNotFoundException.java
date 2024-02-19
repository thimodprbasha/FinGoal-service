package com.example.fingoal.exception;

import lombok.RequiredArgsConstructor;

public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(String message) {
        super(message);
    }
}
