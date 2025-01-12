package com.sg.katabankaccountmanagement.domain;

import com.sg.katabankaccountmanagement.domain.enums.OperationType;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Builder
@Data
public class Operation {

    private Long id;
    private OperationType type;
    private LocalDateTime date;
    private BigDecimal amount;
    private Account account;

    boolean isNegativeAmount() {
        return amount == null || amount.compareTo(BigDecimal.ZERO) < 1;
    }


}
