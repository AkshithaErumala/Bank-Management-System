package com.bank.management.model;

import java.time.LocalDate;
import java.util.ArrayList;

public class Customer {
    private String customerId;
    private String name;
    private LocalDate dob;
    private String phoneNumber;
    private String address;
    private String adhaarNumber;
    private String panNumber;
    private String kycStatus;
    private ArrayList<Account> accounts;

    public Customer(String customerId,String name,LocalDate dob,String phoneNumber,String address,String adhaarNumber,String panNumber,String kycStatus) {
        this.customerId = customerId;
        this.name = name;
        this.dob = dob;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.adhaarNumber = adhaarNumber;
        this.panNumber = panNumber;
        this.kycStatus = kycStatus;
        this.accounts = new ArrayList<>();
    }

    public String getCustomerId() {
        return customerId;
    }

    public String getName() { return name; }

    public String getAddress() {
        return address;
    }

    public LocalDate getDob() {
        return dob;
    }

    public String getMobileNumber() {
        return phoneNumber;
    }

    public String getAdharNumber() {
        return adhaarNumber;
    }

    public String getPanNumber() {
        return panNumber;
    }

    public String getKycStatus() {
        return kycStatus;
    }

    public ArrayList<Account> getAccounts() {
        return accounts;
    }

}
