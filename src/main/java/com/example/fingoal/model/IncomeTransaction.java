package com.example.fingoal.model;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder(toBuilder = true)
@Entity
@Table(name = "income_transactions")
public class IncomeTransaction extends  Transaction{

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "account_id" , referencedColumnName = "id")
    private Account account;

}
