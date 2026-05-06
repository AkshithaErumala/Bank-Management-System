package com.bank.management.service;

import com.bank.management.model.Account;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.InputMismatchException;
import java.util.Scanner;

public class TransactionService {
    AccountService accountService;
    Scanner scanner;
    public TransactionService(AccountService accountService,Scanner scanner)
    {
        this.accountService=accountService;
        this.scanner=scanner;
    }
    public void deposit() {
        int attempts=1;
        System.out.println("Enter Account Number");
        while(attempts <= 3) {
        String accountNumber=scanner.nextLine();
            if (accountService.isAccountNumberValid(accountNumber)) {
                Account account = accountService.findAccountByAccountNumber(accountNumber);
                if (account == null) {
                    System.out.println("Account Not Found");
                } else {
                    System.out.println("Enter Amount to Deposit");
                    try {
                        double amount = Double.parseDouble(scanner.nextLine());
                        if (isAmountValid(amount)) {
                            account.setBalance(account.getBalance() + amount);
                            System.out.printf("₹%,.2f%n", amount);
                            System.out.println("Deposited Successfully");
                            System.out.printf("Balance is ₹%,.2f%n", account.getBalance());
                            Transaction transaction = new Transaction("CREDITED", amount, LocalDateTime.now());
                            account.getTransactions().add(transaction);
                            return;
                        } else {
                            System.out.println("Enter amount greater than 0");
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("please enter valid amount");
                        scanner.nextLine();
                    }
                }
            } else {
                System.out.println("Invalid Account Number");
                attempts++;
            }
        }
        System.out.println("Too many failed attempts returning to menu");
    }
    public void withdraw()
    {
        int attempts=1;
        System.out.println("Enter Account Number");
        while(attempts <= 3) {
        String accountNumber=scanner.nextLine();
            if (accountService.isAccountNumberValid(accountNumber)) {
                Account account = accountService.findAccountByAccountNumber(accountNumber);
                if (account == null) {
                    System.out.println("Account Not Found");
                } else {
                    System.out.println("Enter Amount to Withdraw");
                        try {
                            double amount = Double.parseDouble(scanner.nextLine());
                            if (isAmountValid(amount)) {
                                if (amount <= account.getBalance()) {
                                    account.setBalance(account.getBalance() - amount);
                                    System.out.printf("₹%,.2f%n", amount);
                                    System.out.println("Withdrawn Successfully");
                                    System.out.printf("Balance is ₹%,.2f%n ", account.getBalance());
                                    Transaction transaction = new Transaction("DEBITED", amount, LocalDateTime.now());
                                    account.getTransactions().add(transaction);
                                    return;
                                } else {
                                    System.out.println("Insufficient Balance");
                                    return;
                                }
                            } else {
                                System.out.println("Enter amount greater than 0");
                            }
                        } catch (NumberFormatException e) {
                            System.out.println("please enter valid amount");
                            scanner.nextLine();
                        }

                }
            } else {
                System.out.println("Invalid Account Number");
                attempts++;
            }
        }
        System.out.println("Too many failed attempts returning to menu");
    }

    public boolean isAmountValid(double amount)
    {
        return amount > 0;
    }

    public void transactionHistory() {
        int attempts=1;
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        System.out.println("Enter Account Number");
        while(attempts <= 3) {
            String accountNumber = scanner.nextLine();
            if (accountService.isAccountNumberValid(accountNumber)) {
                Account account = accountService.findAccountByAccountNumber(accountNumber);
                if (account == null) {
                    System.out.println("Account Not Found");
                } else {
                    if (account.getTransactions().isEmpty()) {
                        System.out.println("No Transactions found for this account.");
                        return;
                    }
                    for (Transaction t : account.getTransactions()) {
                        System.out.println(t.amount + "   " + t.type + "  " + t.dateTime.format(dtf));
                    }
                    return;
                }
            } else {
                System.out.println("Invalid Account Number");
                attempts++;
            }
        }
        System.out.println("Too many failed attempts returning to menu");
    }

    public void checkBalance()
    {
        int attempts=1;
        System.out.println("Enter Account Number");
        while(attempts <= 3) {
            String accountNumber = scanner.nextLine();
            if (accountService.isAccountNumberValid(accountNumber)) {
                Account account = accountService.findAccountByAccountNumber(accountNumber);
                if (account == null) {
                    System.out.println("Account Not Found");
                    return;
                } else {
                    System.out.print("Balance: ");
                    System.out.printf("₹%,.2f%n", account.getBalance());
                    return;
                }
            } else {
                System.out.println("Invalid Account Number");
                attempts++;
            }
        }
        System.out.println("Too many failed attempts returning to menu");

    }


}
