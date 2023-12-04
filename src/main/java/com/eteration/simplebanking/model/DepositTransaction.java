package com.eteration.simplebanking.model;




import java.util.Date;


public class DepositTransaction implements Transaction {
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

    public DepositTransaction(double amount) {
        this.date = new Date();
        this.amount = amount;
    }

    @Override
    public void post(Account account) {
        account.credit(amount);
    }
}
