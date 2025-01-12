package com.sg.katabankaccountmanagement.infrastructure.entities;

import com.sg.katabankaccountmanagement.domain.enums.OperationType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "OPERATION")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
public class OperationEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(value = EnumType.STRING)
    private OperationType type;

    private LocalDateTime date;

    private BigDecimal amount;

    @ManyToOne
    @JoinColumn(name = "account_id")
    private AccountEntity account;
}
