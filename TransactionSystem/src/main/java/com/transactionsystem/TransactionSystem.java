package com.transactionsystem;

import java.util.List;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

class Transaction {
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

class Account {
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


class Bank {
    private List<Account> accounts;
    private String name;

    public Bank(String name) {
        // constructor
        this.name = name;
        this.accounts = new ArrayList<Account>();
    }
    public Account openAccount(String name) {
        // opens an account
        Account account = getAccount(name);
        if(account == null){
            account = new Account();
            account.setName(name);
            accounts.add(account);
            return account;
        }else{
            throw new IllegalArgumentException("Account already exists");
        }
    }
    public void closeAccount(String name) {
        // closes an account
        Account accountToRemove = getAccount(name);
        if(accountToRemove != null) {
            accounts.remove(accountToRemove);
        }else {
            throw new IllegalArgumentException("Account does not exist");
        }

    }
    public Account getAccount(String name) {
        // returns the account with the specified name
        for(Account account: accounts) {
            if(account.getName().equals(name)) {
                return account;
            }
        }
        return null;
    }
    public Account getAccountAt(int index){
        return accounts.get(index);
    }
    public List<Account> getAccounts() {
        // returns all the accounts
        return this.accounts;
    }
    public void displayAccounts(){
        System.out.println("Accounts in " + getName() + ":");
        if(getAccounts().isEmpty()){
            System.out.println("No accounts in Bank.");
        }else {
            for (Account account : getAccounts()) {
                System.out.println("Name: " + account.getName() + ", Balance: " + account.getBalance());
            }
        }
    }
    public String getName() {
        // returns the bank's name
        return this.name;
    }
}

public class TransactionSystem {
    public static void mainMenu(Bank bank){
        Scanner keyBoard = new Scanner(System.in);
        int choice;

        while(true){

            System.out.println("\n\t\t\tTransaction System\n\n");
            System.out.println("----------------------------------------------------");
            System.out.println("1. Open a Bank Account.");
            System.out.println("2. Close a bank Account.");
            System.out.println("3. Log into your account.");
            System.out.println("4. Exit Program.");
            System.out.print("Enter Your Choice: ");
            choice = keyBoard.nextInt();
            double balance;
            String name;
            Account account;

            switch(choice){
                case 1:
                    System.out.print("\nEnter your legal Full name: ");
                    keyBoard.nextLine();
                    name = keyBoard.nextLine();
                    System.out.print("\nMust deposit at  least $1 to open account: ");
                    balance = keyBoard.nextDouble();
                    if(balance > 0){
                        System.out.print("\nAdding " + " $" + balance + " to your account.");
                        bank.openAccount(name);
                        account = bank.getAccount(name);
                        account.deposit(balance);
                    } else if (balance <= 0) {
                        throw new IllegalStateException("Unexpected value: " + balance);
                    }
                    break;
                case 2:
                    System.out.print("\nEnter full name on Account: ");
                    keyBoard.nextLine();
                    name = keyBoard.nextLine();
                    bank.closeAccount(name);
                    break;
                case 3:
                    System.out.print("\nEnter full name on Account: ");
                    keyBoard.nextLine();
                    name = keyBoard.nextLine();
                    account = bank.getAccount(name);
                    accountMenu(account);
                    break;
                case 4:
                    System.exit(0);
                    break;
                default:
                    throw new IllegalStateException("Unexpected value: " + choice);
            }

        }

    }

    public static void accountMenu(Account account){
        Scanner keyBoard = new Scanner(System.in);
        int choice;
        double balance;
        boolean check = false;

        while(!check){
            System.out.println("\n\t\t\tAccount menu for " + account.getName() + "\n\n");
            System.out.println("----------------------------------------------------");
            System.out.println("1. Deposit money into your Account.");
            System.out.println("2. Withdraw money from your Account.");
            System.out.println("3. Show balance of account.");
            System.out.println("4. Show Account details.");
            System.out.println("5. Show transactions history on Account.");
            System.out.println("6. Exit to Main Menu and Logout.");
            System.out.print("Enter Your Choice: ");
            choice = keyBoard.nextInt();

            switch(choice){
                case 1:
                    System.out.print("\nEnter deposit amount: ");
                    balance = keyBoard.nextDouble();
                    account.deposit(balance);
                    System.out.println("\nNew account balance: " + account.getBalance());
                    break;
                case 2:
                    System.out.print("\nEnter amount to withdraw: ");
                    balance = keyBoard.nextDouble();
                    account.withdraw(balance);
                    System.out.println("\nNew account balance: " + account.getBalance());
                    break;
                case 3:
                    System.out.print("\nShowing balance for your account: "+ account.getBalance());
                    break;
                case 4:
                    System.out.println("\nShowing Account details:");
                    System.out.print("\nName on Account: " + account.getName());
                    System.out.print("\nAccount Balance Remaining: " + account.getBalance());
                    break;
                case 5:
                    System.out.println("\nShowing Transaction History of " + account.getName() + ":");
                    account.displayTransactions();
                    break;
                case 6:
                    check = true;
                    break;
                default:
                    throw new IllegalStateException("Unexpected value: " + choice);
            }
        }
    }
    public static void main(String args[]){
        Bank bank = new Bank("Bank of America");
        mainMenu(bank);

    }

}
