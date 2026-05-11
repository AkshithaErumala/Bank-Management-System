package com.bank.management.model;

import java.time.LocalDateTime;
import java.util.ArrayList;

public abstract class Account {

    protected double balance;
    protected String accountNumber;
    protected ArrayList<Transaction> transactions;

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public double getBalance() {
        return balance;
    }

    public ArrayList<Transaction> getTransactions() {
        return transactions;
    }


    public Account(String accountNumber) {
        this.accountNumber = accountNumber;
        this.balance=0;
        this.transactions = new ArrayList<>();
    }

    public boolean deposit(double amount)
    {
        balance+=amount;
        Transaction transaction = new Transaction("CREDITED", amount, LocalDateTime.now());
        getTransactions().add(transaction);
        return true;
    }

    public  abstract WithdrawStatus withdraw(double amount);
}
