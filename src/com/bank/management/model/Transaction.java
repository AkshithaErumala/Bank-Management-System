package com.bank.management.model;

import java.time.LocalDateTime;

public class Transaction {
    private String type;
    private double amount;
    private LocalDateTime dateTime;

    public Transaction(String type, double amount, LocalDateTime dateTime) {
        this.type = type;
        this.amount = amount;
        this.dateTime = dateTime;
    }
    public String getType() {
        return type;
    }
    public double getAmount() {
        return amount;
    }
    public LocalDateTime getDateTime() {
        return dateTime;
    }

}
