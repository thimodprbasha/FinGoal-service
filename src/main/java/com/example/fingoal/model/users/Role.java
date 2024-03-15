package com.example.fingoal.model.users;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;


@RequiredArgsConstructor
public enum Role  {
    ADMIN ,

    USER,
    MERCHANT;

    public boolean isRoleValid(Role checkRole){
        for (Role role: Role.values()) {
            if (checkRole.equals(role)){
                return true;
            }
        }
        return false;
    }



}






