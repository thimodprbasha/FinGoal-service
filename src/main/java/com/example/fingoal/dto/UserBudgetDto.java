package com.example.fingoal.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
//TODO add json property name later
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserBudgetDto {
//    @JsonProperty("user_id")
    public Long userId;
//    @JsonProperty("user_id")
    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    public String budgetName;

    public LocalDate startDate;

    public String accountId;

    public String setAmount;

    public List<TransactionCategoryDto> categories;
//
//    public List<IncomeTransactionDto>  incomeTransactions;
//
//    public List<IncomeTransactionDto>  outcomeTransactions;

}
