package com.example.fingoal.controller;

import com.example.fingoal.dto.RequestUserBudgetDto;
import com.example.fingoal.dto.ResponseUserBudgetDto;
import com.example.fingoal.dto.TransactionDto;
import com.example.fingoal.model.User;
import com.example.fingoal.service.budgetService.BudgetService;
import com.example.fingoal.service.userService.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/user/budget")

@RequiredArgsConstructor
@PreAuthorize("hasAuthority('USER')")
public class BudgetController {

    private final UserService userService;

    private final BudgetService budgetService;

    @PostMapping("/create")
    public ResponseEntity<ResponseUserBudgetDto> createBudget(
            @Valid
            @RequestBody ResponseUserBudgetDto budget,
            @AuthenticationPrincipal UserDetails userDetails
    ) {
        User connectedUser = (User) userDetails;
        User user = userService.findUser(connectedUser.getId());
        ResponseUserBudgetDto response = budgetService.createBudget(budget , user);
        return new ResponseEntity<>(response , HttpStatus.CREATED);
    }

    @GetMapping("/get")
    @PreAuthorize("@securityAuthorizeHandler.ifBudgetPresent(authentication)")
    public ResponseEntity<ResponseUserBudgetDto> getBudget(
            @Valid
            @AuthenticationPrincipal UserDetails userDetails
    ) {
        ResponseUserBudgetDto response = budgetService.findUserBudgetByUserMapToDto(((User) userDetails).getId());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/get-all-transactions")
    @PreAuthorize("@securityAuthorizeHandler.ifBudgetPresent(authentication)")
    public ResponseEntity<Page<TransactionDto>> getAllTransactions(
            @Valid
            @AuthenticationPrincipal UserDetails userDetails,
            @RequestParam(name = "pageNo" , defaultValue = "0") Integer pageNo,
            @RequestParam(name = "pageSize" , defaultValue = "10") Integer pageSize
    ) {
        Pageable pageable =  PageRequest.of(pageNo ,pageSize);
        User conectedUser = (User) userDetails;
        Page<TransactionDto> response = budgetService.findAllIncomeAndOutcomeTransactions(conectedUser.getUserBudget().getId() ,pageable);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping("/update")
    @PreAuthorize("@securityAuthorizeHandler.ifBudgetPresent(authentication)")
    public ResponseEntity<ResponseUserBudgetDto> updateBudget(
            @Valid
            @AuthenticationPrincipal UserDetails userDetails,
            @RequestBody RequestUserBudgetDto requestUserBudgetDto
    ) {
        User conectedUser = (User) userDetails;
        if (requestUserBudgetDto.hasAtLeastOneValue()){
            ResponseUserBudgetDto response = budgetService.updateUserBudget(conectedUser.getId() , requestUserBudgetDto);
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
        return ResponseEntity.badRequest().build();
    }

    @DeleteMapping("/delete/{user-id}")
    public ResponseEntity<?> deleteBudget(
            @Valid
            @PathVariable(name = "user-id") long id
    ) {
        budgetService.deleteBudget(id);
        return ResponseEntity.ok().build();
    }
}
