package com.eteration.simplebanking.services;


import com.eteration.simplebanking.BankAccountRepository;
import com.eteration.simplebanking.Response.DepositResponse;
import com.eteration.simplebanking.entity.BankAccountEntity;
import com.eteration.simplebanking.entity.DepositTransactionEntity;
import com.eteration.simplebanking.entity.WithdrawalTransactionEntity;
import com.eteration.simplebanking.model.InsufficientBalanceException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.UUID;

@Service
public class BankAccountService {

    private final BankAccountRepository bankAccountRepository;

    private final TransactionService transactionService;


    public BankAccountService(BankAccountRepository bankAccountRepository, TransactionService transactionService) {
        this.bankAccountRepository = bankAccountRepository;
        this.transactionService = transactionService;
    }


    public void save(BankAccountEntity account){

        bankAccountRepository.save(account);

    }


    public BankAccountEntity findAccount(String accountNumber) {
        return bankAccountRepository.findByAccountNumber(accountNumber);
    }
}