package com.example.fingoal.model;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.Immutable;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.math.BigDecimal;
import java.time.LocalDateTime;
@Getter
@Setter
@Immutable
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder(toBuilder = true)
@Inheritance(strategy = InheritanceType.JOINED)
@EntityListeners(AuditingEntityListener.class)
@Entity
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @CreatedDate
    @Column(
            nullable = false,
            updatable = false
    )
    private LocalDateTime createdAt;

    @Column(nullable = false)
    private LocalDateTime transactionDate;

    @Column(nullable = false)
    private BigDecimal amount;

    @Column(nullable = false)
    private String remarks;

    private String attachment;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TransactionType transactionType;

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
