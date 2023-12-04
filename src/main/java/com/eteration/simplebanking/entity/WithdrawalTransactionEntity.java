package com.eteration.simplebanking.entity;

import javax.persistence.Entity;
import java.util.Date;

@Entity

public class WithdrawalTransactionEntity extends TransactionEntity {
    public WithdrawalTransactionEntity(Long id, Date date, double amount) {
        super(id, date, amount);
    }

    public WithdrawalTransactionEntity() {

    }


    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
