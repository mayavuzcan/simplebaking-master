package com.eteration.simplebanking.entity;

import lombok.EqualsAndHashCode;

import javax.persistence.Entity;

@EqualsAndHashCode(callSuper = true)
@Entity
public class DepositTransactionEntity extends TransactionEntity{
    public DepositTransactionEntity() {
    }

}
