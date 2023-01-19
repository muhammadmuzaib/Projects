package com.transactionsystem;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Account {
    String name;
    private double balance;
    private List<Transaction> transactions;

    //Constructor
    public Account() {
        this.name = "";
        this.balance = 0.0;
        this.transactions = new ArrayList<Transaction>();
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setBalance(double balance){
        this.balance = balance;
    }
    public String getName() {
        return this.name;
    }
    public void deposit(double amt) {
        // deposit method
        this.balance += amt;
        Date timestamp = new Date();
        Transaction transaction = new Transaction(amt, "deposit", timestamp);
        transactions.add(transaction);
    }
    public void withdraw(double amt) {
        // withdraw method
        if (amt > getBalance()) {
            throw new IllegalArgumentException("Insufficient funds.");
        } else {
            this.balance -= amt;
            Date timestamp = new Date();
            Transaction transaction = new Transaction(amt, "withdrawal", timestamp);
            transactions.add(transaction);
        }
    }
    public double getBalance() {
        // returns the balance
        return this.balance;
    }
    public void addTransaction(Transaction t) {
        // adds a transaction to the list
        transactions.add(t);
    }
    public List<Transaction> getTransactions(){
        return transactions;
    }
    public void displayTransactions(){
        if(getTransactions().isEmpty()){
            System.out.println("\nNo Transaction in Account.");
        }else{
            for(Transaction transaction: getTransactions()){
                System.out.println("Transaction Type: " + transaction.getType() +
                        "\t\t Amount: " + transaction.getAmount() +
                        "\t\t Time Stamp: " + transaction.getTimestamp());
            }
        }
    }
    public Transaction getTransactionAt(int index){
        return transactions.get(index);
    }
}
