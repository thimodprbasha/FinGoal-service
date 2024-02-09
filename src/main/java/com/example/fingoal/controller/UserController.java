package com.example.fingoal.controller;
import com.example.fingoal.dto.RegisterRequestDto;
import com.example.fingoal.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
public class UserController {
    @GetMapping("/helloUser")
    public ResponseEntity<String> helloUser() {
        return ResponseEntity.ok("Hello user");
    }
}
