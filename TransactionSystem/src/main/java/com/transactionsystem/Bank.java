package com.transactionsystem;

import java.util.ArrayList;
import java.util.List;

public class Bank {
    private List<Account> accounts;
    private String name;

    public Bank(String name) {
        // constructor
        this.name = name;
        this.accounts = new ArrayList<Account>();
    }
    public Account openAccount(String name) {
        try {
            Account account = getAccount(name);
            if(account == null){
                account = new Account();
                account.setName(name);
                accounts.add(account);
                return account;
            }else{
                throw new IllegalArgumentException("Account already exists");
            }
        } catch(IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
            return null;
        }
    }

    public void closeAccount(String name) {
        try {
            Account accountToRemove = getAccount(name);
            if(accountToRemove != null) {
                accounts.remove(accountToRemove);
            }else {
                throw new IllegalArgumentException("Account does not exist");
            }
        } catch(IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
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
