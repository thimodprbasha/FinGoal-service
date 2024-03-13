package com.example.fingoal.dto;

import com.example.fingoal.model.Account;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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
