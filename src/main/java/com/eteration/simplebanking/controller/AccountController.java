package com.eteration.simplebanking.controller;

import com.eteration.simplebanking.Request.DepositRequest;
import com.eteration.simplebanking.Request.WithdrawalRequest;
import com.eteration.simplebanking.Response.AccountDetailsResponse;
import com.eteration.simplebanking.Response.DepositResponse;
import com.eteration.simplebanking.entity.BankAccountEntity;
import com.eteration.simplebanking.entity.DepositTransactionEntity;
import com.eteration.simplebanking.entity.WithdrawalTransactionEntity;
import com.eteration.simplebanking.model.InsufficientBalanceException;
import com.eteration.simplebanking.services.BankAccountService;
import com.eteration.simplebanking.services.TransactionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.UUID;


@RestController
@RequestMapping("/api/bank-accounts")
public class AccountController {
    private final BankAccountService bankAccountService;
    private final TransactionService transactionService;

    public AccountController(
            BankAccountService bankAccountService,
            TransactionService transactionService) {
        this.bankAccountService = bankAccountService;
        this.transactionService = transactionService;
    }

    @PostMapping("/credit/{accountNumber}")
    public ResponseEntity<Object> credit(
            @PathVariable String accountNumber,
            @RequestBody DepositRequest depositRequest) {

        BankAccountEntity bankAccount = bankAccountService.findAccount(accountNumber);

        if (bankAccount == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Account not found");
        }

        double amount = depositRequest.getAmount();
        bankAccount.setBalance(amount + bankAccount.getBalance());

        DepositTransactionEntity depositTransaction = new DepositTransactionEntity();
        depositTransaction.setAmount(amount);
        depositTransaction.setDate(new Date());
        transactionService.saveTransaction(depositTransaction);

        bankAccountService.save(bankAccount);

        DepositResponse depositResponse = new DepositResponse("OK", UUID.randomUUID().toString());
        return ResponseEntity.status(HttpStatus.OK).body(depositResponse);
    }

    @PostMapping("/debit/{accountNumber}")
    public ResponseEntity<Object> debit(
            @PathVariable String accountNumber,
            @RequestBody WithdrawalRequest withdrawalRequest) throws InsufficientBalanceException {

        BankAccountEntity bankAccount = bankAccountService.findAccount(accountNumber);

        if (bankAccount == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Account not found");
        }

        double amount = withdrawalRequest.getAmount();

        double currentBalance = bankAccount.getBalance();

        if (currentBalance < amount) {
            throw new InsufficientBalanceException("Yetersiz Bakiye");

        }


        bankAccount.setBalance(bankAccount.getBalance()-amount);

        WithdrawalTransactionEntity withdrawalTransaction = new WithdrawalTransactionEntity();
        withdrawalTransaction.setAmount(amount);
        withdrawalTransaction.setDate(new Date());
        transactionService.saveTransaction(withdrawalTransaction);

        bankAccountService.save(bankAccount);

        DepositResponse depositResponse = new DepositResponse("OK", UUID.randomUUID().toString());
        return ResponseEntity.status(HttpStatus.OK).body(depositResponse);    }

    @GetMapping("/{accountNumber}")
    public ResponseEntity<Object> getAccount(@PathVariable String accountNumber) {
        BankAccountEntity bankAccount = bankAccountService.findAccount(accountNumber);

        if (bankAccount == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Account not found");
        }

        AccountDetailsResponse accountDetailsResponse;
        accountDetailsResponse = new AccountDetailsResponse(
                bankAccount.getAccountNumber(),
                bankAccount.getOwner(),
                bankAccount.getBalance(),
                bankAccount.getCreateDate(),
                null);

        return ResponseEntity.status(HttpStatus.OK).body(accountDetailsResponse);
    }
}