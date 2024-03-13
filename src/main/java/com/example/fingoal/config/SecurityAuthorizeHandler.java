package com.example.fingoal.config;

import com.example.fingoal.exception.ResourceNotFoundException;
import com.example.fingoal.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class SecurityAuthorizeHandler {
    public boolean ifBudgetPresent(Authentication authentication) {
        User connectedUser = (User) authentication.getPrincipal();
        if (Optional.ofNullable(connectedUser.getUserBudget()).isPresent()){
            return true;
        }else {
            throw  new ResourceNotFoundException(
                    "Budget not have been created"
                    , HttpStatus.FORBIDDEN);
        }
    }
}
