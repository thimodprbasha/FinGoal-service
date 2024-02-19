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
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "transaction_categories")
public class TransactionCategory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;

    private String categoryName;

    private String icon;

    private BigDecimal setAmount;

    private BigDecimal currentAmount;

    @OneToMany(mappedBy = "category")
    private List<IncomeTransaction> incomeTransactions;

    @OneToMany(mappedBy = "category")
    private List<OutcomeTransaction> outcomeTransactions;

    @OneToMany(mappedBy = "category")
    private List<Transfer> transfers;

    @ManyToOne
    @JoinColumn(name = "user_budget_id" , referencedColumnName = "id")
    private UserBudget userBudget;
}
