package com.example.fingoal.controller;

import com.example.fingoal.model.Role;
import com.example.fingoal.service.AdminService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/admin")
@RequiredArgsConstructor
public class AdminController {

    private final AdminService adminService;

    @GetMapping("/get-users")
    public ResponseEntity<?> register(
            @Valid @RequestParam(name = "ROLE")  String param ,
            @RequestParam(name = "pageNo" , defaultValue = "0") Integer pageNo,
            @RequestParam(name = "pageSize" , defaultValue = "10") Integer pageSize
    ) {
        final String role = param.toUpperCase();
        Pageable pageable =  PageRequest.of(pageNo ,pageSize);
        if (role.equals(Role.ADMIN.name())){
            return new ResponseEntity<>(adminService.getAllUsersByRole(Role.ADMIN , pageable) , HttpStatus.OK);
        } else if (role.equals(Role.USER.name())) {
            return new ResponseEntity<>(adminService.getAllUsersByRole(Role.USER , pageable) , HttpStatus.OK);
        }
        return ResponseEntity.badRequest().body("Invalid Role!");
    }
}
