package com.example.fingoal.model;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;

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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "transaction_category_id" , referencedColumnName = "id")
    private TransactionCategory category;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_budget_id" , referencedColumnName = "id")
    private UserBudget userBudget;

}
