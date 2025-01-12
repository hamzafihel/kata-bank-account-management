package com.sg.katabankaccountmanagement.domain.exceptions;

import com.sg.katabankaccountmanagement.domain.enums.OperationType;

public class UnauthorizedTransactionException extends RuntimeException {

    private static final String MESSAGE = "Unauthorized transaction %s on account %s";

    public UnauthorizedTransactionException(OperationType type, String accountNumber) {
        super(String.format(MESSAGE, type.toString(), accountNumber));
    }
}
