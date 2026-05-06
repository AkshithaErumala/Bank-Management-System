package com.bank.management.service;

import com.bank.management.model.Account;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Scanner;

public class Transaction {
    String type;
    double amount;
    LocalDateTime dateTime;

    public Transaction(String type, double amount, LocalDateTime dateTime) {
        this.type = type;
        this.amount = amount;
        this.dateTime = dateTime;
    }


}
