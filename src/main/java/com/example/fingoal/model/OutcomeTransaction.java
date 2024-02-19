package com.example.fingoal.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "outcome_transactions")
public class OutcomeTransaction{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;

    private LocalDateTime transactionDate;

    private BigDecimal amount;

    private String remarks;

    private String attachment;

    @ManyToOne
    @JoinColumn(name = "account_id" , referencedColumnName = "id")
    private Account account;

    @ManyToOne
    @JoinColumn(name = "transaction_category_id" , referencedColumnName = "id")
    private TransactionCategory category;

    @ManyToOne
    @JoinColumn(name = "merchant_id" , referencedColumnName = "id")
    private Merchant merchant;

    @ManyToOne
    @JoinColumn(name = "user_budget_id" , referencedColumnName = "id")
    private UserBudget userBudget;
}
