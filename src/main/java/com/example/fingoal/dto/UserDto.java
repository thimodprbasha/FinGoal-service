package com.example.fingoal.dto;

import com.example.fingoal.model.users.Role;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

//TODO add json property name later
//TODO add validation
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {

    private Long id;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    private String firstName;

    private String lastName;

    private String email;

    private String telephone;

    private String profilePicture;

    private boolean isEnabled;

    private Long userBudgetId;

    @Enumerated(EnumType.STRING)
    private Role role;

    private List<AccountDto> accounts;
}
