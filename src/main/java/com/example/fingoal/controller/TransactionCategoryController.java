package com.example.fingoal.controller;

import com.example.fingoal.dto.TransactionCategoryDto;
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
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/v1/user/transaction-category")
@RequiredArgsConstructor
public class TransactionCategoryController {

    private final BudgetService budgetService;

    private final TransactionCategoryService transactionCategoryService;

    @PostMapping("/create/{user-id}")
    public ResponseEntity<?> createCategory(
            @Valid
            @PathVariable(name = "user-id") long id ,
            @RequestBody TransactionCategoryDto transactionCategoryDto
    ) {
        var budget = budgetService.userBudgetFindByUser(id);
        var resp = transactionCategoryService.createTransactionCategory(transactionCategoryDto, budget);
        return new ResponseEntity(resp , HttpStatus.CREATED);
    }

    @GetMapping("/get-all/{user-id}")
    public ResponseEntity<?> getAllCategories(
            @Valid
            @PathVariable(name = "user-id") long id,
            @RequestParam(name = "pageNo" , defaultValue = "0") Integer pageNo,
            @RequestParam(name = "pageSize" , defaultValue = "10") Integer pageSize
    ) {
        Pageable pageable =  PageRequest.of(pageNo ,pageSize);
        var budget = budgetService.userBudgetFindByUser(id);
        Page<TransactionCategoryDto> transactionCategoryDtos = transactionCategoryService.findAllCategoryByBudget(budget.getId(), pageable);
        return new ResponseEntity<>(transactionCategoryDtos , HttpStatus.OK);
    }

    @GetMapping("/get-by")
    public ResponseEntity<?> getCategory(
            @Valid
            @RequestParam(name = "category-id" , required = false) Optional<Long> categoryId,
            @RequestParam(name = "category" , required = false) String categoryName
    ) {
        if (categoryId.isPresent() ){
            return new ResponseEntity<>(transactionCategoryService.findByCategoryIdMapToDto(categoryId.get()) , HttpStatus.OK);
        } else if (StringUtils.isNotEmpty(categoryName)) {
            return new ResponseEntity<>(transactionCategoryService.findByCategoryNameMapToDto(categoryName) , HttpStatus.OK);
        }
        return ResponseEntity.badRequest().body("Invalid");
    }

    @PutMapping("/create/{category-id}")
    public ResponseEntity<?> updateCategory(
            @Valid
            @PathVariable(name = "category-id") long id ,
            @RequestBody TransactionCategoryDto transactionCategoryDto
    ) {
        var budget = budgetService.userBudgetFindByUser(id);
        var resp = transactionCategoryService.updateCategory(transactionCategoryDto, budget);
        return new ResponseEntity<>(resp , HttpStatus.CREATED);
    }

    @GetMapping("/delete/{category-id}")
    public ResponseEntity<?> deleteCategory(
            @Valid
            @PathVariable(name = "category-id") long categoryId
    ) {
        transactionCategoryService.deleteCategory(categoryId);
        return  ResponseEntity.ok().build();
    }


}
