package com.example.fingoal.dto;

import com.example.fingoal.model.Account;
import com.example.fingoal.model.TransactionCategory;
import com.example.fingoal.model.UserBudget;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class IncomeTransactionDto {
    private Long id;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    private BigDecimal amount;

    private LocalDate transactionDate;

    private String remarks;

    private String attachment;

    private Long accountId;

    private Long categoryId;

    private Long userBudgetId;
}
