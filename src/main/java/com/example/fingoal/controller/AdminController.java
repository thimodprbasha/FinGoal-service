package com.example.fingoal.controller;

import com.example.fingoal.model.users.Role;
import com.example.fingoal.service.userService.AdminService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/admin")
@RequiredArgsConstructor
@PreAuthorize("hasAuthority('ADMIN')")
public class AdminController {

    private final AdminService adminService;

    @GetMapping("/get-users")
    public ResponseEntity<?> register(
            @Valid @RequestParam(name = "ROLE")  String roleParam,
            @RequestParam(name = "pageNo" , defaultValue = "0") Integer pageNo,
            @RequestParam(name = "pageSize" , defaultValue = "10") Integer pageSize
    ) {
        final Role role;
        try{
            role = Role.valueOf(roleParam.toUpperCase());
        }catch (IllegalArgumentException ignored){
            return ResponseEntity.badRequest().body("Invalid Role!");
        }

        Pageable pageable =  PageRequest.of(pageNo ,pageSize);
        switch (role){
            case USER -> {
                return new ResponseEntity<>(
                        adminService.getAllUsersByRole(Role.USER , pageable) ,
                        HttpStatus.OK
                );
            }
            case ADMIN -> {
                return new ResponseEntity<>(
                        adminService.getAllUsersByRole(Role.ADMIN , pageable) ,
                        HttpStatus.OK
                );
            }
            case MERCHANT ->{
                return new ResponseEntity<>(
                        adminService.getAllUsersByRole(Role.MERCHANT , pageable) ,
                        HttpStatus.OK)
                        ;
            }
            default -> {
                return new ResponseEntity<>(
                        adminService.getAllUsers(pageable) ,
                        HttpStatus.OK)
                        ;
            }

        }

    }
}
