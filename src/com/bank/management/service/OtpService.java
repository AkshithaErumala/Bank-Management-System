package com.bank.management.service;


import java.util.Scanner;

public class OtpService {
    Scanner scanner;
    int SystemOtp;
    int userOtp;

    public OtpService(Scanner scanner)
    {
        this.scanner=scanner;
    }
    public int generateOtp(String PhoneNumber)
    {
        SystemOtp = (int)(Math.random() * 9999) + 1000;
        System.out.println("otp has been sent to registered mobile number XXXXXX"+PhoneNumber.substring(6,10));
        System.out.println("OTP: "+SystemOtp);
        return SystemOtp;
    }

    public boolean isOtpVerified()
    {
        System.out.println("Enter OTP: ");
        int wrongOtpAttempts=1;
        while(wrongOtpAttempts < 4) {
            userOtp = Integer.parseInt(scanner.nextLine());
            if(userOtp == SystemOtp)
            {
                return true;
            }
            else {
                System.out.println("Invalid OTP try again.");
            }
            wrongOtpAttempts++;
        }
        System.out.println("Too many incorrect OTP attempts.Please try again after some time.");
        return false;
    }



}
