package com.sg.katabankaccountmanagement.domain;

import com.sg.katabankaccountmanagement.domain.enums.OperationType;
import com.sg.katabankaccountmanagement.domain.exceptions.UnauthorizedTransactionException;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class AccountTest {

    @Test
    public void should_throw_exception_when_deposit_negative_amount() {

        // Given
        Operation operation = Operation.builder().id(1L).type(OperationType.DEPOSIT).date(LocalDateTime.now()).amount(new BigDecimal(-190)).build();
        Account account = Account.builder().accountNumber("139P560").operations(Collections.emptyList()).balance(new BigDecimal(100)).build();

        // When
        UnauthorizedTransactionException exception = assertThrows(UnauthorizedTransactionException.class, () -> account.addOperation(operation));

        // Then
        assertEquals("Unauthorized transaction DEPOSIT on account 139P560", exception.getMessage());
    }

    @Test
    public void should_throw_exception_when_exceeds_balance() {

        // Given
        Operation operation = Operation.builder().id(1L).type(OperationType.WITHDRAWAL).date(LocalDateTime.now()).amount(new BigDecimal(1500)).build();
        Account account = Account.builder().accountNumber("986L236").operations(Collections.emptyList()).balance(new BigDecimal(1000)).build();

        // When
        UnauthorizedTransactionException exception = assertThrows(UnauthorizedTransactionException.class, () -> account.addOperation(operation));

        // Then
        assertEquals("Unauthorized transaction WITHDRAWAL on account 986L236", exception.getMessage());
    }

    @Test
    public void should_process_valid_withdrawal() {

        // Given
        Operation operation = Operation.builder().id(1L).type(OperationType.WITHDRAWAL).date(LocalDateTime.now()).amount(new BigDecimal(800)).build();
        Account account = Account.builder().accountNumber("145K369").balance(new BigDecimal(2000)).build();

        // When
        account.addOperation(operation);

        // Then
        assertEquals(1, account.getOperations().size());
        assertEquals(new BigDecimal(1200), account.getBalance());
    }

    @Test
    public void should_process_valid_deposit() {

        // Given
        Operation operation = Operation.builder().id(1L).type(OperationType.DEPOSIT).date(LocalDateTime.now()).amount(new BigDecimal(1820)).build();
        Account account = Account.builder().accountNumber("475V145").balance(new BigDecimal(5000)).build();

        // When
        account.addOperation(operation);

        // Then
        assertEquals(1, account.getOperations().size());
        assertEquals(new BigDecimal(6820), account.getBalance());
    }
}
