package com.sg.katabankaccountmanagement.domain.exceptions;

public class BankAccountException extends Exception {

    public BankAccountException(ExceptionCode exceptionCode) {
        super(exceptionCode.getMessage());
    }
}
