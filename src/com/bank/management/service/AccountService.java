package com.bank.management.service;

import com.bank.management.model.Account;

import java.nio.file.PathMatcher;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

public class AccountService
{
    Scanner scanner;
    public AccountService(Scanner scanner)
    {
        this.scanner=scanner;
        otpService=new OtpService(scanner);
    }
    String AccountNumber;
    String CustomerId;
    String KYCStatus;
    LocalDate dob=null;
    int age;
    int AccountCounter = 10001;
    int CustomerCounter = 50001;
    OtpService otpService;
    Account account;
    ArrayList<Account> accountList=new ArrayList<Account>();
    public void createAccount()
    {
        String Name=getValidName();
        LocalDate dob=getValidDob();
        String AdharNumber=getValidAdhar();
        String PanCardNumber=getValidPanNumber();
        String PhoneNumber=getValidMobileNumber();
        System.out.println("Enter Address");
        String Address=scanner.nextLine();


        if(isEligible())
        {
            otpService.generateOtp(PhoneNumber);
        }
        else {
            System.out.println("You must be at least 18 years old to open a bank account.");
            return;
        }
        if(isVerified())
        {
            AccountNumber=generateAccountNumber();
            CustomerId=generateCustomerId();
            KYCStatus="VERIFIED";
            account=new Account(Name,dob,PhoneNumber,Address,AdharNumber,PanCardNumber,AccountNumber,CustomerId,KYCStatus);
            accountList.add(account);
            System.out.println("Account Created Successfully");
            System.out.println("ACCOUNT NUMBER : "+AccountNumber);
            System.out.println("CUSTOMER ID : "+CustomerId);
        }
        else {
            System.out.println("Account has not been created");
        }
    }


    public LocalDate getValidDob()
    {
        System.out.println("Enter Date Of Birth: (dd-MM-YYY)");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        while(true)
        {
            try{
                String BirthDate=scanner.nextLine();
                dob=LocalDate.parse(BirthDate,formatter);
                return dob;
            }
            catch(Exception e)
            {
                System.out.println("Please enter in the given format");
            }
        }
    }


    public String getValidName()
    {
        System.out.println("Enter your name:");
        while(true) {
            String name=scanner.nextLine();
            if(name.matches("[a-zA-Z]+"))
            {
                return name;
            }
            else {
                System.out.println("please enter a valid name.");
            }
        }
    }


    public int calculateAge(LocalDate dob)
    {
        Period years=Period.between(dob, LocalDate.now());
        age=years.getYears();
        return age;
    }


    public String getValidAdhar()
    {
        System.out.println("Enter your Adhar Number: ");
        while(true) {
            String AdharNumber = scanner.nextLine();
            if(AdharNumber.matches("\\d{12}"))
            {
                return AdharNumber;
            }
            else  {
                System.out.println("please enter a valid Adhar Number.");
            }
        }
    }


    public String getValidPanNumber() {
        System.out.println("Enter your Pan Number: ");
        while (true) {
            String PanCardNumber=scanner.nextLine();
            if(PanCardNumber.matches("[A-Z]{5}[0-9]{4}[A-Z]{1}"))
            {
                return PanCardNumber;
            }
            else {
                System.out.println("please enter a valid Pan Number.");
            }
        }
    }

    public String getValidMobileNumber()
    {
        System.out.println("Enter your phone Number: ");
        while(true) {
            String PhoneNumber=scanner.nextLine();
            if(PhoneNumber.matches("[0-9]{10}"))
            {
                return PhoneNumber;
            }
            else {
                System.out.println("please enter a valid phone number.");
            }
        }
    }
    public boolean isEligible()
    {
        return calculateAge(dob)>=18;
    }

    boolean isVerified()
    {
        return otpService.isOtpVerified();
    }

    String generateAccountNumber()
    {
        return "ACC"+AccountCounter++;
    }

    String generateCustomerId()
    {
        return "CUST"+CustomerCounter++;
    }

    public Account findAccountByAccountNumber(String accountNumber)
    {
        for(Account account:accountList)
        {
            if(accountNumber.equals(account.getAccountNumber()))
            {
                return account;
            }
        }
        return null;
    }

    public boolean isAccountNumberValid(String accountNumber)
    {
        return (accountNumber.matches("ACC\\d{5}"));
    }
}
