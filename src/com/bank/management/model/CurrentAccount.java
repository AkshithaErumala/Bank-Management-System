package com.bank.management.model;

import java.time.LocalDateTime;

public class CurrentAccount extends Account {

    public CurrentAccount(String accountNumber) {
        super(accountNumber);
    }

    @Override
    public WithdrawStatus withdraw(double amount) {
        if(amount <= balance)
        {
            balance = balance - amount;
            Transaction transaction = new Transaction("DEBITED", amount, LocalDateTime.now());
            getTransactions().add(transaction);
            return WithdrawStatus.SUCCESS;
        }
        return WithdrawStatus.INSUFFICIENT_BALANCE;
    }
}
