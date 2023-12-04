package com.eteration.simplebanking.services;

import com.eteration.simplebanking.TransactionRepository;
import com.eteration.simplebanking.entity.TransactionEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransactionService {
    private final TransactionRepository transactionRepository;


    public TransactionService(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    public TransactionEntity saveTransaction(TransactionEntity transaction) {
        return transactionRepository.save(transaction);
    }


}
