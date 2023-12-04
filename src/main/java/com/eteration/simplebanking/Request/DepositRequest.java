package com.eteration.simplebanking.Request;


public  class DepositRequest {
    private double amount;

    public DepositRequest(double amount) {
        this.amount = amount;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }
}