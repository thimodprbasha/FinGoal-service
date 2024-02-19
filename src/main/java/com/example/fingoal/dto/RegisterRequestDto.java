package com.example.fingoal.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
//TODO add json property name later
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequestDto {

    @NotBlank(message = "First name cant be empty")
    private String firstName;

    @NotBlank(message = "LastName cant be empty")
    private String lastName;

    @NotBlank(message = "Email cant be empty")
    @Email
    private String email;

    private String telephone;

    @NotBlank(message = "Password cant be empty")
    @Size(min = 4)
    private String password;

    private String profilePicture;

}
