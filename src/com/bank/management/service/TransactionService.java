package com.bank.management.service;

import com.bank.management.model.*;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;


public class TransactionService {
    AccountService accountService;
    Scanner scanner;

    public TransactionService(AccountService accountService, Scanner scanner) {
        this.accountService = accountService;
        this.scanner = scanner;
    }

    public void deposit() {
        int attempts = 1;
        while (attempts <= 3) {
            System.out.println("Enter Account Number");
            String accountNumber = scanner.nextLine();
            AccountSearchResult statusAndResult = accountService.findAccountByAccountNumber(accountNumber);
            switch (statusAndResult.getStatus()) {
                case ACCOUNT_FOUND:
                Account account = statusAndResult.getAccount();
                while(true) {
                    System.out.println("Enter Amount to Deposit");
                    try {
                        double amount = Double.parseDouble(scanner.nextLine());
                        if (isAmountValid(amount)) {
                            boolean success = account.deposit(amount);
                            if (success) {
                                System.out.printf("₹%,.2f%n", amount);
                                System.out.println("Deposited Successfully");
                                System.out.printf("Balance is ₹%,.2f%n", account.getBalance());
                                return;
                            }
                        } else {
                            System.out.println("Enter amount greater than 0");
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("please enter valid amount");
                    }
                }
                case ACCOUNT_NOT_FOUND:
                    System.out.println("Account Not Found");
                    return;
                case INVALID_ACCOUNT_FORMAT:
                    System.out.println("Invalid Account Number");
                    attempts++;
            }
        }
        System.out.println("Too many failed attempts returning to menu");
    }

    public void withdraw() {
        int attempts = 1;
        while (attempts <= 3) {
            System.out.println("Enter Account Number");
            String accountNumber = scanner.nextLine();
            AccountSearchResult statusAndResult = accountService.findAccountByAccountNumber(accountNumber);
            switch(statusAndResult.getStatus()) {
                case  ACCOUNT_FOUND:
                Account account = statusAndResult.getAccount();
                while (true) {
                    System.out.println("Enter Amount to Withdraw");
                    try {
                        double amount = Double.parseDouble(scanner.nextLine());
                        if (isAmountValid(amount)) {
                            WithdrawStatus status = account.withdraw(amount);
                            switch (status) {
                                case SUCCESS:
                                    System.out.printf("₹%,.2f%n", amount);
                                    System.out.println("Withdrawn Successfully");
                                    System.out.printf("Balance is ₹%,.2f%n ", account.getBalance());
                                    return;
                                case INSUFFICIENT_BALANCE:
                                    System.out.println("Insufficient Balance");
                                    return;
                                case MINIMUM_BALANCE_VIOLATION:
                                    System.out.println("Withdrawal denied. Minimum balance of ₹1,000 must be maintained in Savings Account.");
                                    return;
                            }
                        } else {
                            System.out.println("Enter amount greater than 0");
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("please enter valid amount");
                    }
                }
                case ACCOUNT_NOT_FOUND:
                    System.out.println("Account Not Found");
                    return;
                case INVALID_ACCOUNT_FORMAT:
                    System.out.println("Invalid Account Format");
                    attempts++;
            }
        }
        System.out.println("Too many failed attempts returning to menu");
    }

    public boolean isAmountValid(double amount) {
        return amount > 0;
    }

    public void transactionHistory() {
        int attempts = 1;
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        while (attempts <= 3) {
            System.out.println("Enter Account Number");
            String accountNumber = scanner.nextLine();
            AccountSearchResult statusAndResult = accountService.findAccountByAccountNumber(accountNumber);
            switch (statusAndResult.getStatus()) {
                case ACCOUNT_FOUND:
                    Account account = statusAndResult.getAccount();
                    if (account.getTransactions().isEmpty()) {
                        System.out.println("No Transactions found for this account.");
                        return;
                    }
                    for (Transaction t : account.getTransactions()) {
                        System.out.println(t.getAmount() + "   " + t.getType() + "  " + t.getDateTime().format(dtf));
                    }
                    return;
                case ACCOUNT_NOT_FOUND:
                    System.out.println("Account not found");
                    return;
                case INVALID_ACCOUNT_FORMAT:
                    System.out.println("Invalid Account Format");
                    attempts++;
            }
        }
        System.out.println("Too many failed attempts returning to menu");
    }

    public void checkBalance() {
        int attempts = 1;
        while (attempts <= 3) {
            System.out.println("Enter Account Number");
            String accountNumber = scanner.nextLine();
            AccountSearchResult statusAndResult = accountService.findAccountByAccountNumber(accountNumber);
            switch (statusAndResult.getStatus()) {
                case ACCOUNT_FOUND:
                    Account account = statusAndResult.getAccount();
                    System.out.println("Balance is " + account.getBalance());
                    return;
                case ACCOUNT_NOT_FOUND:
                    System.out.println("Account not found");
                    return;
                case INVALID_ACCOUNT_FORMAT:
                    System.out.println("Invalid Account Format");
                    attempts++;
            }
        }
        System.out.println("Too many failed attempts returning to menu");

    }
}

