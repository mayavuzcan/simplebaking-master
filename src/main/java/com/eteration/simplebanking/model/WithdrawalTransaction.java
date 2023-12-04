package com.eteration.simplebanking.model;




import java.util.Date;

// This class is a place holder you can change the complete implementation
public class WithdrawalTransaction implements Transaction {
    private Date date;
    private double amount;

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }


    public WithdrawalTransaction(double amount) {
        this.date = new Date();
        this.amount = amount;
    }


    @Override
    public void post(Account account) throws InsufficientBalanceException {
        account.debit( amount);
    }
}


