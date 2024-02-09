package com.example.fingoal.model;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;


@RequiredArgsConstructor
public enum Role  {
    ADMIN ,
    USER
}


