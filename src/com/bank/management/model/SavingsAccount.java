package com.bank.management.model;

import java.time.LocalDateTime;

public class SavingsAccount extends Account {
    double minimumBalance=1000;
    public SavingsAccount(String accountNumber) {
        super(accountNumber);
    }

    public WithdrawStatus withdraw(double amount) {
        if(balance-amount < minimumBalance){
            return WithdrawStatus.MINIMUM_BALANCE_VIOLATION;
        }
        balance = balance - amount;
        Transaction transaction = new Transaction("DEBITED", amount, LocalDateTime.now());
        getTransactions().add(transaction);
        return WithdrawStatus.SUCCESS;
    }

}
