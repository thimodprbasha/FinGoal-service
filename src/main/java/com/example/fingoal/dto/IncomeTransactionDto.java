package com.example.fingoal.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import lombok.extern.jackson.Jacksonized;

@Getter
@Setter
@Jacksonized
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder(toBuilder = true)
public class IncomeTransactionDto extends TransactionDto {

    private Long accountId;
}
