package com.example.fingoal.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "user_budget")
@EntityListeners(AuditingEntityListener.class)
public class UserBudget {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @CreatedDate
    @Column(
            nullable = false,
            updatable = false
    )
    private LocalDateTime createdAt;

    @LastModifiedDate
    @Column(insertable = false)
    private LocalDateTime updatedAt;

    @Column(nullable = false)
    private String budgetName;

    private BigDecimal currentAmount;

    private BigDecimal currentSavings;

    @Column(nullable = false)
    private BigDecimal budgetAmount;

    private BigDecimal incomeAmount;

    private BigDecimal outcomeAmount;

    private BigDecimal categoryTotalAmount;

    private boolean isCategoryFull;

    private LocalDate startDate;

    @JsonIgnore
    @OneToMany(
            cascade = CascadeType.ALL,
            fetch = FetchType.EAGER,
            mappedBy = "userBudget"
    )
    private List<IncomeTransaction> incomeTransactions;

    @JsonIgnore
    @OneToMany(
            cascade = CascadeType.ALL,
            fetch = FetchType.EAGER,
            mappedBy = "userBudget"
    )
    private List<OutcomeTransaction> outcomeTransactions;

    @JsonIgnore
    @OneToMany(
            cascade = CascadeType.ALL,
            fetch = FetchType.EAGER,
            mappedBy = "userBudget"
    )
    private List<Transfer> transfers;

    @JsonIgnore
    @OneToMany(
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY,
            mappedBy = "userBudget"
    )
    private List<TransactionCategory> transactionCategories;

    @OneToOne(cascade = CascadeType.ALL , fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;
}
