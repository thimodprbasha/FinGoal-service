package com.example.fingoal.dto;

import com.example.fingoal.model.Account;
import com.example.fingoal.model.Role;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

//TODO add json property name later
//TODO add validation
@Data
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
