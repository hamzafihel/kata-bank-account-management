package com.sg.katabankaccountmanagement.infrastructure.mappers;

import com.sg.katabankaccountmanagement.domain.Account;
import com.sg.katabankaccountmanagement.domain.Operation;
import com.sg.katabankaccountmanagement.infrastructure.entities.AccountEntity;
import com.sg.katabankaccountmanagement.infrastructure.entities.OperationEntity;

public class OperationMapper {

    public static OperationEntity from(Operation operation) {

        return OperationEntity.builder()
                .id(operation.getId())
                .amount(operation.getAmount())
                .type(operation.getType())
                .date(operation.getDate())
                .account(AccountEntity.builder()
                        .id(operation.getAccount().getId())
                        .accountNumber(operation.getAccount().getAccountNumber())
                        .balance(operation.getAccount().getBalance())
                        .build())
                .build();
    }

    public static Operation to(OperationEntity operationEntity) {

        return Operation.builder()
                .id(operationEntity.getId())
                .amount(operationEntity.getAmount())
                .date(operationEntity.getDate())
                .type(operationEntity.getType())
                .account(Account.builder()
                        .id(operationEntity.getAccount().getId())
                        .accountNumber(operationEntity.getAccount().getAccountNumber())
                        .balance(operationEntity.getAccount().getBalance())
                        .build())
                .build();
    }
}
