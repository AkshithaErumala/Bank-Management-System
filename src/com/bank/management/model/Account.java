package com.bank.management.model;

import com.bank.management.service.Transaction;

import java.time.LocalDate;
import java.util.ArrayList;

public class Account {
    private String name;
    private LocalDate dob;
    private String address;
    private String mobileNumber;
    private String adharNumber;
    private String panNumber;
    private String kycStatus;
    private String accountNumber;
    private String customerId;
    private double balance;
    private ArrayList<Transaction> transactions;

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public LocalDate getDob() {
        return dob;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public String getAdharNumber() {
        return adharNumber;
    }

    public String getPanNumber() {
        return panNumber;
    }

    public String getKycStatus() {
        return kycStatus;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public String getCustomerId() {
        return customerId;
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


    public Account(String name, LocalDate dob, String mobileNumber, String address, String adharNumber, String panNumber, String accountNumber, String customerId, String kycStatus) {
        this.name = name;
        this.dob = dob;
        this.address = address;
        this.mobileNumber = mobileNumber;
        this.adharNumber = adharNumber;
        this.panNumber = panNumber;
        this.accountNumber = accountNumber;
        this.customerId = customerId;
        this.kycStatus = kycStatus;
        this.balance=0;
        this.transactions = new ArrayList<>();
    }


}
