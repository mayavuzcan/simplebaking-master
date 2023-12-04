package com.eteration.simplebanking.entity;


import javax.persistence.*;
import java.util.Date;

@Entity
public class TransactionEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Temporal(TemporalType.TIMESTAMP)
    private Date date;

    private double amount;

    public TransactionEntity(Long id, Date date, double amount) {
        this.id = id;
        this.date = date;
        this.amount = amount;
    }

    public TransactionEntity() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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
}
