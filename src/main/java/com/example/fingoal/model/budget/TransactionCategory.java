package com.example.fingoal.model.budget;

import com.example.fingoal.model.budget.OutcomeTransaction;
import com.example.fingoal.model.budget.UserBudget;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "transaction_categories")
@EntityListeners(AuditingEntityListener.class)
public class TransactionCategory {
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
    private String categoryName;

    private String icon;

    @Column(nullable = false)
    private BigDecimal setAmount;

    private BigDecimal currentAmount;

    @JsonIgnore
    @OneToMany(
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY,
            mappedBy = "category"
    )
    private List<OutcomeTransaction> outcomeTransactions;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_budget_id" , referencedColumnName = "id")
    private UserBudget userBudget;

    @CreatedBy
    @Column(
            nullable = false,
            updatable = false
    )
    private Long createdBy;

    @LastModifiedBy
    @Column(insertable = false)
    private Long lastModifiedBy;
}
