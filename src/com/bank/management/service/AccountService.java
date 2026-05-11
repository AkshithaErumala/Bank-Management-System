package com.bank.management.service;

import com.bank.management.model.*;
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
    String accountNumber;
    String customerId;
    String kycStatus;
    LocalDate dob=null;
    int age;
    int AccountCounter = 10001;
    int CustomerCounter = 50001;
    Customer customer;
    OtpService otpService;
    Account account;
    ArrayList<Customer> customers=new ArrayList();
    public void createAccount()
    {
        System.out.println("Select Account Type: ");
        System.out.println("1. SAVINGS ACCOUNT");
        System.out.println("2. CURRENT ACCOUNT");
        int choice = Integer.parseInt(scanner.nextLine());
        String name=getValidName();
        LocalDate dob=getValidDob();
        String adhaarNumber=getValidAdhar();
        String panNumber=getValidPanNumber();
        String phoneNumber=getValidMobileNumber();
        System.out.println("Enter Address");
        String address=scanner.nextLine();


        if(isEligible())
        {
            otpService.generateOtp(phoneNumber);
        }
        else {
            System.out.println("You must be at least 18 years old to open a bank account.");
            return;
        }
        if(isVerified())
        {
            accountNumber=generateAccountNumber() ;
            customerId=generateCustomerId();
            kycStatus="VERIFIED";
            customer=new Customer(customerId,name,dob,phoneNumber,address,adhaarNumber,panNumber,kycStatus);
            if(choice == 1)
            {
                account=new SavingsAccount(accountNumber);
            }
            if(choice == 2) {
                account=new CurrentAccount(accountNumber);
            }
            else {
                System.out.println("Incorrect choice");
                return;
            }
            customers.add(customer);
            customer.getAccounts().add(account);
            System.out.println("Account Created Successfully");
            System.out.println("ACCOUNT NUMBER : "+accountNumber);
            System.out.println("CUSTOMER ID : "+customerId);
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
            if(name.matches("[a-zA-Z ]+"))
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

    public AccountSearchResult findAccountByAccountNumber(String accountNumber)
    {
        if(!isAccountNumberValid(accountNumber))
        {
            return new AccountSearchResult(AccountSearchStatus.INVALID_ACCOUNT_FORMAT,null);
        }
        for(Customer customer:customers)
        {
            for(Account  account:customer.getAccounts()) {
                if (accountNumber.equals(account.getAccountNumber())) {
                    return new AccountSearchResult(AccountSearchStatus.ACCOUNT_FOUND,account);
                }
            }
        }
        return new AccountSearchResult(AccountSearchStatus.ACCOUNT_NOT_FOUND,null);
    }


    public boolean isAccountNumberValid(String accountNumber)
    {
        return (accountNumber.matches("ACC\\d{5}"));
    }

}
