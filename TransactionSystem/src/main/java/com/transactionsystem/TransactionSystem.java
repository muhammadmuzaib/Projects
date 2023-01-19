package com.transactionsystem;
import java.util.InputMismatchException;
import java.util.Scanner;

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
                    try{
                        balance = keyBoard.nextDouble();
                        if(balance > 0){
                            System.out.print("\nAdding " + " $" + balance + " to your account.");
                            bank.openAccount(name);
                            account = bank.getAccount(name);
                            account.deposit(balance);
                        } else if (balance <= 0) {
                            throw new IllegalArgumentException("Deposit must be greater than 0, entered: " + balance);
                        }
                    }catch(InputMismatchException e){
                        System.out.println("Invalid input. Please enter a valid number.");
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
