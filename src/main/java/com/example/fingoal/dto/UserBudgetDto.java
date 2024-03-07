package com.example.fingoal.dto;

import com.example.fingoal.model.UserBudget;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
//TODO add json property name later
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserBudgetDto {

    private Long id;
//    @JsonProperty("user_id")
    public Long userId;
//    @JsonProperty("user_id")
    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    public String budgetName;

    public LocalDate startDate;

    private BigDecimal budgetAmount;

    private BigDecimal currentAmount;

    private BigDecimal currentSavings;

    private BigDecimal incomeAmount;

    private BigDecimal outcomeAmount;

    private BigDecimal categoryTotalAmount;

    private boolean isCategoryFull;

    public List<TransactionCategoryDto> transactionCategories;
//
    public List<IncomeTransactionDto>  incomeTransactions;
//
    public List<OutcomeTransactionDto>  outcomeTransactions;

}
