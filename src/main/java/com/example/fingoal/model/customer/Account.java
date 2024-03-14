package com.example.fingoal.model.customer;

import com.example.fingoal.model.budget.IncomeTransaction;
import com.example.fingoal.model.budget.OutcomeTransaction;
import com.example.fingoal.model.users.Role;
import com.example.fingoal.model.users.User;
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
@Table(name = "accounts")
@EntityListeners(AuditingEntityListener.class)
public class Account {

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
    private String accountName;

    @Column(nullable = false)
    private String accountType;

    @Column(
            nullable = false,
            updatable = false,
            unique = true
    )
    private String accountNumber;

    @Column(nullable = false)
    private BigDecimal balance;

    @JsonIgnore
    @OneToMany(
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY,
            mappedBy = "account"
    )
    private List<IncomeTransaction> incomeTransactions;

    @JsonIgnore
    @OneToMany(
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY,
            mappedBy = "account"
    )
    private List<OutcomeTransaction> outcomeTransactions;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id" , referencedColumnName = "id")
    private User user;

    @CreatedBy
    @Column(
            nullable = false,
            updatable = false
    )
    private Long createdBy;

    @LastModifiedBy
    @Column(insertable = false)
    private Long lastModifiedBy;

    @PreUpdate
    @PrePersist
    public void onUpdate() {
        if (this.user.isRoleEquals(Role.ADMIN)
        ) {
            throw new IllegalStateException("Only users with role 'USER' or 'MERCHANT' are allowed to Persist or Update");
        }
    }


}

