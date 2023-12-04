package com.eteration.simplebanking.model;




import java.util.ArrayList;
import java.util.List;


public class Account{

    private String owner;
    private String accountNumber;;
    private Double balance;
    private List<Transaction> transactions;

    public Account() {
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

    public List<Transaction> getTransactions() {
        return transactions;
    }

    public void setTransactions(List<Transaction> transactions) {
        this.transactions = transactions;
    }

    public Account(String owner, String number) {
        this.owner = owner;
        this.accountNumber = number;
        this.balance = 0.00;
        this.transactions = new ArrayList<>();
    }
    public void debit(double amount) throws InsufficientBalanceException {
        if (amount <= balance) {
            balance -= amount;
        } else {
            System.out.println("Insufficient funds");
            throw new InsufficientBalanceException("Yetersiz Bakiye");
        }
    }

    public void credit(double amount) {
        balance += amount;
    }

    public void post(Transaction transaction) throws InsufficientBalanceException {
        transaction.post(this);
        transactions.add(transaction);
        this.setTransactions(transactions);
    }

}
