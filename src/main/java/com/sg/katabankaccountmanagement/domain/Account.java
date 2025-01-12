package com.sg.katabankaccountmanagement.domain;

import com.sg.katabankaccountmanagement.domain.exceptions.UnauthorizedTransactionException;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Builder
@Data
public class Account {

    private Long id;
    private String accountNumber;
    private BigDecimal balance;
    @Builder.Default
    private List<Operation> operations = new ArrayList<>();
    private Customer customer;


    public void addOperation(Operation operation) {

        if (operation.isNegativeAmount()) {
            throw new UnauthorizedTransactionException(operation.getType(), accountNumber);
        }

        BigDecimal newBalanceValue = getNewBalanceValue(operation);

        if (!isPermittedOperation(newBalanceValue)) {
            throw new UnauthorizedTransactionException(operation.getType(), accountNumber);
        }

        this.balance = newBalanceValue;
        this.operations.add(operation);

    }


    private BigDecimal getNewBalanceValue(Operation operation) {
        return switch (operation.getType()) {
            case DEPOSIT -> this.balance.add(operation.getAmount());
            case WITHDRAWAL -> this.balance.subtract(operation.getAmount());
        };

    }

    private boolean isPermittedOperation(BigDecimal newBalanceValue) {
        return newBalanceValue.compareTo(BigDecimal.ZERO) >= 0;
    }


}
