package com.example.fingoal.dto;

import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RequestUserBudgetDto {

    public String budgetName;

    public LocalDate startDate;

    private BigDecimal budgetAmount;
    public boolean hasAtLeastOneValue() {
        return budgetName != null && !budgetName.isEmpty() ||
                startDate != null ||
                budgetAmount != null;
    }
}
