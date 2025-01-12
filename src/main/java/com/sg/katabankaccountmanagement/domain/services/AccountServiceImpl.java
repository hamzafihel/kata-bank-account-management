package com.sg.katabankaccountmanagement.domain.services;

import com.sg.katabankaccountmanagement.domain.Account;
import com.sg.katabankaccountmanagement.domain.Operation;
import com.sg.katabankaccountmanagement.domain.exceptions.BankAccountException;
import com.sg.katabankaccountmanagement.domain.exceptions.ExceptionCode;
import com.sg.katabankaccountmanagement.domain.repositories.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {

    private final AccountRepository accountRepository;

    @Override
    public List<Operation> findOperationsHistory(String accountNumber) throws BankAccountException {

        Account account = getAccountByAccountNumber(accountNumber);

        return account.getOperations();
    }

    @Transactional
    @Override
    public void saveOperation(String accountNumber, Operation operation) throws BankAccountException {

        Account account = getAccountByAccountNumber(accountNumber);
        operation.setAccount(account);
        account.addOperation(operation);

        accountRepository.saveAccount(account);
    }


    private Account getAccountByAccountNumber(String accountNumber) throws BankAccountException {
        return accountRepository.findAccountByAccountNumber(accountNumber)
                .orElseThrow(() -> new BankAccountException(ExceptionCode.ACCOUNT_NOT_FOUND));
    }
}
