package com.example.fingoal.dto;

import com.example.fingoal.model.users.Role;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.Optional;

//TODO add json property name later
@Getter
@Setter
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

//    @NotBlank(message = "Role cant be empty")
//    private Role role;
//
//    public boolean isRoleEquals(Role role){
//        return this.role.equals(role);
//    }
//
//    public boolean isRoleEmpty(){
//        return Optional.ofNullable(this.role).isEmpty();
//    }


}
