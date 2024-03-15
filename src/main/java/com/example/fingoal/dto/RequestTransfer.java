package com.example.fingoal.dto;

import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RequestTransfer {

    private BigDecimal amount;

    private Long fromAccount;

    private Long toAccount;
}
