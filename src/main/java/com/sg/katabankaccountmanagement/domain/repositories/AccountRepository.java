package com.sg.katabankaccountmanagement.domain.repositories;

import com.sg.katabankaccountmanagement.domain.Account;

import java.util.Optional;

public interface AccountRepository {

    Optional<Account> findAccountByAccountNumber(String accountNumber);

    void saveAccount(Account account);

}
