package com.bank.management;

import com.bank.management.controllers.BankController;

public class Main {
    public static void main(String[] args) {
        BankController bankController=new BankController();
        bankController.start();
    }
}
