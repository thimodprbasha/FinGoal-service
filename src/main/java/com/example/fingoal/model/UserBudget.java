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
@Table(name = "user_budget")
public class UserBudget {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;

    private BigDecimal totalAmount;

    private BigDecimal budgetAmount;

    private BigDecimal incomeAmount;

    private BigDecimal outcomeAmount;

    private LocalDateTime endDate;

    @OneToMany(mappedBy = "userBudget")
    private List<IncomeTransaction> incomeTransactions;

    @OneToMany(mappedBy = "userBudget")
    private List<OutcomeTransaction> outcomeTransactions;

    @OneToMany(mappedBy = "userBudget")
    private List<Transfer> transfers;

    @ManyToOne()
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

}
