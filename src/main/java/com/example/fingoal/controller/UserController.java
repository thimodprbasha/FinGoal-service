package com.example.fingoal.controller;
import com.example.fingoal.dto.TransactionCategoryDto;
import com.example.fingoal.service.userService.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/create/{user-id}")
    public ResponseEntity<?> updateUser(
            @Valid
            @PathVariable(name = "user-id") long id ,
            @RequestBody TransactionCategoryDto transactionCategoryDto
    ) {

        return ResponseEntity.ok().build();
    }
}
