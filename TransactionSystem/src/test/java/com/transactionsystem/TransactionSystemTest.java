package com.transactionsystem;

import org.junit.Before;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
class TransactionSystemTest {

    private Bank bank = new Bank("My Bank");
    private Account account;
    @Test
    public void testOpenAccount() {
        account = bank.openAccount("John Doe");
        assertEquals("John Doe", account.getName());
    }

    @Test
    public void testDeposit() {
        Bank bank = new Bank("My Bank");
        account = bank.openAccount("John Doe");
        account.deposit(100);
        assertEquals(100, account.getBalance(), 0.1);
    }

    @Test
    public void testWithdraw() {
        Bank bank = new Bank("My Bank");
        account = bank.openAccount("John Doe");
        account.deposit(500.0);
        account.withdraw(500.0);
        assertEquals(0.0, account.getBalance(), 0.0001);
    }

    @Test
    public void testInsufficientFunds() {
        Bank bank = new Bank("My Bank");
        account = bank.openAccount("John Doe");
        assertThrows(IllegalArgumentException.class, () -> account.withdraw(1000.0));
    }

    @Test
    public void testCloseAccount() {
        Bank bank = new Bank("My Bank");
        account = bank.openAccount("John Doe");
        bank.closeAccount("John Doe");
        assertNull(bank.getAccount("John Doe"));
    }
}