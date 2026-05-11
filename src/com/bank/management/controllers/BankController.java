package com.bank.management.controllers;

import com.bank.management.service.*;
import java.util.Scanner;


public class BankController {
    private Scanner scanner;
    AccountService accountService;
    TransactionService transactionService;
    public BankController() {
        this.scanner=new Scanner(System.in);
        this.accountService=new AccountService(scanner);
        this.transactionService=new TransactionService(accountService, scanner);
    }
    public void start()
    {
        switchMenu();
    }
    public void switchMenu()
    {
        while(true)
        {
            System.out.println("1->Open Account");
            System.out.println("2->Deposit");
            System.out.println("3->Withdraw");
            System.out.println("4->Transaction History");
            System.out.println("5->Check Balance");
            System.out.println("0->Exit");
            System.out.println("Select your choice");
            int userChoice = Integer.parseInt(scanner.nextLine());
            if(userChoice == 0) {
                System.out.println("EXIT");
                return;
            }
                switch (userChoice) {
                    case 1:
                        accountService.createAccount();
                        break;
                    case 2:
                        transactionService.deposit();
                        break;
                    case 3:
                        transactionService.withdraw();
                        break;
                    case 4:
                        transactionService.transactionHistory();
                        break;
                    case 5:
                        transactionService.checkBalance();
                        break;
                    default:
                        System.out.println("Wrong choice");
                        break;
                }
            }
    }
}
