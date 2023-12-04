package com.eteration.simplebanking.Request;

import lombok.AllArgsConstructor;
import lombok.Data;


public  class WithdrawalRequest {
    private double amount;

    public WithdrawalRequest(double amount) {
        this.amount = amount;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }
}
