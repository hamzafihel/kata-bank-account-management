package com.sg.katabankaccountmanagement.domain.services;

import com.sg.katabankaccountmanagement.domain.Operation;
import com.sg.katabankaccountmanagement.domain.exceptions.BankAccountException;

import java.util.List;

public interface AccountService {

    List<Operation> findOperationsHistory(String accountNumber) throws BankAccountException;

    void saveOperation(String accountNumber, Operation operation) throws BankAccountException;

}
