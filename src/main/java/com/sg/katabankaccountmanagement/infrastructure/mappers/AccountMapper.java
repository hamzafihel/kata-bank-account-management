package com.sg.katabankaccountmanagement.infrastructure.mappers;

import com.sg.katabankaccountmanagement.domain.Account;
import com.sg.katabankaccountmanagement.infrastructure.entities.AccountEntity;

import java.util.stream.Collectors;

public class AccountMapper {

    public static Account to(AccountEntity accountEntity) {

        return accountEntity == null ? null : Account.builder()
                .id(accountEntity.getId())
                .accountNumber(accountEntity.getAccountNumber())
                .operations(accountEntity.getOperations() != null ? accountEntity.getOperations().stream().map(OperationMapper::to)
                        .collect(Collectors.toList()) : null)
                .balance(accountEntity.getBalance())
                .build();
    }


    public static AccountEntity from(Account account) {

        return account == null ? null : AccountEntity.builder()
                .id(account.getId())
                .accountNumber(account.getAccountNumber())
                .operations(account.getOperations() != null ? account.getOperations().stream().map(OperationMapper::from)
                        .collect(Collectors.toList()) : null)
                .balance(account.getBalance())
                .build();
    }


}
