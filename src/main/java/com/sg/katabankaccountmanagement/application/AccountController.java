package com.sg.katabankaccountmanagement.application;

import com.sg.katabankaccountmanagement.domain.Operation;
import com.sg.katabankaccountmanagement.domain.exceptions.BankAccountException;
import com.sg.katabankaccountmanagement.domain.services.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/account")
@RequiredArgsConstructor
public class AccountController {

    private final AccountService accountService;

    @GetMapping("/{accountNumber}/operations")
    public List<Operation> operationsHistory(@PathVariable String accountNumber) throws BankAccountException {
        return accountService.findOperationsHistory(accountNumber);
    }

    @PostMapping("/{accountNumber}/save-operation")
    public void saveOperation(@PathVariable String accountNumber, @RequestBody Operation operation) throws BankAccountException {
        accountService.saveOperation(accountNumber, operation);
    }

}
