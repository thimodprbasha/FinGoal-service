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
@Table(name = "outcome_transactions")
public class OutcomeTransaction extends Transaction{

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "account_id" , referencedColumnName = "id")
    private Account account;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "transaction_category_id" , referencedColumnName = "id")
    private TransactionCategory category;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "merchant_id" , referencedColumnName = "id")
    private Merchant merchant;

//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "user_budget_id" , referencedColumnName = "id")
//    private UserBudget userBudget;
}
