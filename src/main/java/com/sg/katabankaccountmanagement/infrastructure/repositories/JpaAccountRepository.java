package com.sg.katabankaccountmanagement.infrastructure.repositories;

import com.sg.katabankaccountmanagement.domain.Account;
import com.sg.katabankaccountmanagement.domain.repositories.AccountRepository;
import com.sg.katabankaccountmanagement.infrastructure.mappers.AccountMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class JpaAccountRepository implements AccountRepository {

    private final SpringDataAccountRepository accountRepository;

    @Override
    public Optional<Account> findAccountByAccountNumber(String accountNumber) {
        return Optional.ofNullable(AccountMapper.to(accountRepository.findByAccountNumber(accountNumber)));
    }

    @Override
    public void saveAccount(Account account) {
        accountRepository.save(AccountMapper.from(account));
    }


}
