package com.example.fingoal.controller;

import com.example.fingoal.dto.TransactionCategoryDto;
import com.example.fingoal.model.users.User;
import com.example.fingoal.model.budget.UserBudget;
import com.example.fingoal.service.budgetService.BudgetService;
import com.example.fingoal.service.budgetService.TransactionCategoryService;
import io.micrometer.common.util.StringUtils;
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

import java.util.Optional;

@RestController
@RequestMapping("/api/v1/user/transaction-category")
@RequiredArgsConstructor
@PreAuthorize(
        "hasAuthority('USER') and " +
        "@securityAuthorizeHandler.ifBudgetPresent(authentication)"
)
public class TransactionCategoryController {

    private final BudgetService budgetService;

    private final TransactionCategoryService transactionCategoryService;

    @PostMapping("/create")
    public ResponseEntity<?> createCategory(
            @Valid
            @AuthenticationPrincipal UserDetails userDetails,
            @RequestBody TransactionCategoryDto transactionCategoryDto
    ) {
        UserBudget budget = budgetService.findUserBudgetByUser(((User) userDetails).getId());
        TransactionCategoryDto resp = transactionCategoryService
                .createTransactionCategory(transactionCategoryDto, budget);
        return new ResponseEntity<>(resp , HttpStatus.CREATED);
    }


    @GetMapping("/get-all")
    public ResponseEntity<Page<TransactionCategoryDto>> getAllCategories(
            @Valid
            @AuthenticationPrincipal UserDetails userDetails,
            @RequestParam(name = "pageNo" , defaultValue = "0") Integer pageNo,
            @RequestParam(name = "pageSize" , defaultValue = "10") Integer pageSize
    ) {
        Pageable pageable =  PageRequest.of(pageNo ,pageSize);
        User conectedUser = (User) userDetails;
        budgetService.isUserBudgetExistsOnConnectedUser(conectedUser.getUserBudget());
        Page<TransactionCategoryDto> transactionCategories = transactionCategoryService
                .findAllCategoryByBudget(conectedUser.getUserBudget().getId(), pageable);
        return new ResponseEntity<>(transactionCategories , HttpStatus.OK);
    }

    @GetMapping("/get-by")
    public ResponseEntity<?> getCategory(
            @Valid
            @AuthenticationPrincipal UserDetails userDetails,
            @RequestParam(name = "category-id" , required = false) Optional<Long> categoryId,
            @RequestParam(name = "category" , required = false) String categoryName
    ) {
        if (categoryId.isPresent() ){
            return new ResponseEntity<>(transactionCategoryService.findByCategoryIdMapToDto(categoryId.get()) , HttpStatus.OK);
        } else if (StringUtils.isNotEmpty(categoryName) ) {
            User conectedUser = (User) userDetails;
            budgetService.isUserBudgetExistsOnConnectedUser(conectedUser.getUserBudget());
            return new ResponseEntity<>(transactionCategoryService.findByCategoryNameMapToDto(conectedUser.getUserBudget().getId() , categoryName) , HttpStatus.OK);
        }
        return ResponseEntity.badRequest().body("Invalid");
    }

    @PatchMapping("/update")
    public ResponseEntity<?> updateCategory(
            @Valid
            @AuthenticationPrincipal UserDetails userDetails,
            @RequestBody TransactionCategoryDto transactionCategoryDto
    ) {
        UserBudget budget = budgetService.findUserBudgetByUser(((User) userDetails).getId());
        //Fixme
        var resp = transactionCategoryService.updateCategory(transactionCategoryDto, budget);
        return new ResponseEntity<>(resp , HttpStatus.CREATED);
    }

    @DeleteMapping("/delete/{category-id}")
    public ResponseEntity<?> deleteCategory(
            @Valid
            @PathVariable(name = "category-id") long categoryId
    ) {
        transactionCategoryService.deleteCategory(categoryId);
        return  ResponseEntity.ok().build();
    }


}
