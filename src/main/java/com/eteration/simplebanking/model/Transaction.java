package com.eteration.simplebanking.model;



public interface  Transaction {
    void post(Account account) throws InsufficientBalanceException;

}
