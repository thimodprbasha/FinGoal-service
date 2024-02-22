package com.example.fingoal.dto;

import com.example.fingoal.model.Account;
import com.example.fingoal.model.TransactionCategory;
import com.example.fingoal.model.UserBudget;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class IncomeTransactionDto {
    private Long id;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    private LocalDateTime transactionDate;

    private BigDecimal amount;

    private String remarks;

    private String attachment;

    private Long accountId;

    private Long categoryId;

    private Long userBudgetId;
}
