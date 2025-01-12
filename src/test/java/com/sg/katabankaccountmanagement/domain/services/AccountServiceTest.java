package com.sg.katabankaccountmanagement.domain.services;

import com.sg.katabankaccountmanagement.domain.Account;
import com.sg.katabankaccountmanagement.domain.Operation;
import com.sg.katabankaccountmanagement.domain.enums.OperationType;
import com.sg.katabankaccountmanagement.domain.exceptions.BankAccountException;
import com.sg.katabankaccountmanagement.domain.exceptions.UnauthorizedTransactionException;
import com.sg.katabankaccountmanagement.domain.repositories.AccountRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
public class AccountServiceTest {

    @Mock
    AccountRepository accountRepository;

    @InjectMocks
    AccountServiceImpl accountService;

    @Captor
    private ArgumentCaptor<Account> accountCaptor;

    @Test
    public void should_throw_exception_when_deposit_not_found_account() {

        // GIVEN
        Operation operation = Operation.builder().id(1L).type(OperationType.DEPOSIT).date(LocalDateTime.now()).amount(new BigDecimal(-190)).build();

        // WHEN
        when(accountRepository.findAccountByAccountNumber(anyString())).thenReturn(Optional.empty());

        // THEN
        assertThrows(BankAccountException.class, () -> accountService.saveOperation("178F191", operation));
    }

    @Test
    public void should_throw_exception_when_withdrawal_negative_amount() {

        // GIVEN
        Account account = Account.builder().accountNumber("178F191").operations(Collections.emptyList()).balance(new BigDecimal(1890)).build();
        Operation operation = Operation.builder().id(1L).type(OperationType.DEPOSIT).account(account).date(LocalDateTime.now()).amount(new BigDecimal(-250)).build();

        // WHEN
        when(accountRepository.findAccountByAccountNumber(anyString())).thenReturn(Optional.ofNullable(account));

        // THEN
        assertThrows(UnauthorizedTransactionException.class, () -> accountService.saveOperation("178F191", operation));
    }

    @Test
    public void should_process_deposit_existing_account() throws BankAccountException {

        // GIVEN
        Account account = Account.builder().accountNumber("178F191").operations(new ArrayList<>()).balance(new BigDecimal(1000)).build();
        Operation operation = Operation.builder().id(1L).type(OperationType.DEPOSIT).account(account).date(LocalDateTime.now()).amount(new BigDecimal(150)).build();

        // WHEN
        when(accountRepository.findAccountByAccountNumber(account.getAccountNumber())).thenReturn(Optional.of(account));
        accountService.saveOperation("178F191", operation);

        // THEN
        verify(accountRepository).saveAccount(accountCaptor.capture());

        assertNotNull(accountCaptor.getValue());
        assertEquals(new BigDecimal(1150), accountCaptor.getValue().getBalance());


    }

    @Test
    public void should_process_withdrawal_existing_account() throws BankAccountException {

        // GIVEN
        Account account = Account.builder().accountNumber("123S987").operations(new ArrayList<>()).balance(new BigDecimal(2000)).build();
        Operation operation = Operation.builder().id(1L).type(OperationType.WITHDRAWAL).account(account).date(LocalDateTime.now()).amount(new BigDecimal(500)).build();

        // WHEN
        when(accountRepository.findAccountByAccountNumber(account.getAccountNumber())).thenReturn(Optional.of(account));
        accountService.saveOperation("123S987", operation);

        // THEN
        verify(accountRepository).saveAccount(accountCaptor.capture());

        assertNotNull(accountCaptor.getValue());
        assertEquals(new BigDecimal(1500), accountCaptor.getValue().getBalance());

    }

    @Test
    public void should_throw_exception_when_get_operations_history_not_found_account() {

        // WHEN
        when(accountRepository.findAccountByAccountNumber(anyString())).thenReturn(Optional.empty());

        // THEN
        assertThrows(BankAccountException.class, () -> accountService.findOperationsHistory("199M6553"));
    }

    @Test
    public void should_get_operations_history() throws BankAccountException {

        // GIVEN
        Operation deposit = Operation.builder().id(1L).type(OperationType.DEPOSIT).date(LocalDateTime.now()).amount(new BigDecimal(150)).build();
        Operation withdrawal = Operation.builder().id(2L).type(OperationType.WITHDRAWAL).date(LocalDateTime.now()).amount(new BigDecimal(50)).build();

        Account account = Account.builder().accountNumber("454V222").operations(new ArrayList<>()).balance(new BigDecimal(6500)).build();
        account.setOperations(List.of(deposit, withdrawal));

        // WHEN
        when(accountRepository.findAccountByAccountNumber(account.getAccountNumber())).thenReturn(Optional.of(account));
        List<Operation> operations = accountService.findOperationsHistory(account.getAccountNumber());

        // THEN
        assertEquals(account.getOperations(), operations);

    }


}
