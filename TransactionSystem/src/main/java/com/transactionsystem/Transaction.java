package com.transactionsystem;

import java.util.Date;

public class Transaction {
    private Date timeStamp;
    private double amount;
    private String transactionType;

    //Constructor
    public Transaction(double amt, String t, Date timeStamp) {
        this.amount = amt;
        this.transactionType = t;
        this.timeStamp = timeStamp;
    }

    public void setTimeStamp(Date timeStamp) {
        this.timeStamp = timeStamp;
    }
    public void setAmount(double amount) {
        this.amount = amount;
    }
    public void setTransactionType(String transactionType) {
        this.transactionType = transactionType;
    }
    public Date getTimestamp() {
        return timeStamp;
    }
    public double getAmount() {
        return amount;
    }
    public String getType() {
        return transactionType;
    }
}
