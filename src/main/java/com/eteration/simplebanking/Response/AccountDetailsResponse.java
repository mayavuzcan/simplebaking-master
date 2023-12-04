package com.eteration.simplebanking.Response;

import com.eteration.simplebanking.entity.TransactionEntity;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;
import java.util.List;


public  class AccountDetailsResponse {
    private String accountNumber;
    private String owner;
    private double balance;
    private Date createDate;
    private List<TransactionEntity> transactions;


    public AccountDetailsResponse(String accountNumber, String owner, double balance, Date createDate, List<TransactionEntity> transactions) {
        this.accountNumber = accountNumber;
        this.owner = owner;
        this.balance = balance;
        this.createDate = createDate;
        this.transactions = transactions;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public List<TransactionEntity> getTransactions() {
        return transactions;
    }

    public void setTransactions(List<TransactionEntity> transactions) {
        this.transactions = transactions;
    }
}
