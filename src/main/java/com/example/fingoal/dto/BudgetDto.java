package com.example.fingoal.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;
//TODO add json property name later
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BudgetDto {
//    @JsonProperty("user_id")
    public Long userId;
//    @JsonProperty("user_id")
    public String budgetName;

    public LocalDate startDate;

    public String accountId;

    public String setAmount;

    public List<TransactionCategoryDto> categories;


}
