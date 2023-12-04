package com.eteration.simplebanking.entity;


import javax.persistence.*;
import java.util.Date;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class BankAccountEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String owner;
    private String accountNumber;
    private double balance;

    public BankAccountEntity() {

    }

    public BankAccountEntity(String accountNumber, String owner, double balance, Date date) {
        this.owner = owner;
        this.accountNumber = accountNumber;
        this.balance = balance;
        this.createDate = date;
    }

    @Override
    public String toString() {
        return "BankAccountEntity{" +
                "id=" + id +
                ", owner='" + owner + '\'' +
                ", accountNumber='" + accountNumber + '\'' +
                ", balance=" + balance +
                ", createDate=" + createDate +
                ", updateDate=" + updateDate +
                '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    private Date createDate;
    private Date updateDate;


    // Constructors, getters, and setters
}
