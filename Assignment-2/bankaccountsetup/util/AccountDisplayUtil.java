package com.bankaccountsetup.util;

import com.bankaccountsetup.Interface.InterestCalculation;
import com.bankaccountsetup.models.AccountDetails;

public class AccountDisplayUtil {

    public static void displayAccountDetails(String accountId, AccountDetails account, InterestCalculation interestCalculator) {
        System.out.println("----- " + account.getAccountType() + " Account Details -----");
        System.out.println("Account ID: " + accountId);
        System.out.println("Account Holder Name: " + account.getCustomer().getFullName());
        System.out.println("Mobile Number: " + account.getCustomer().getPhoneNumber());
        System.out.println("PAN Number: " + account.getCustomer().getPanNumber());
        System.out.println("Aadhaar Number: " + account.getCustomer().getAadhaarNumber());
        System.out.println("Branch: " + account.getBranch());
        System.out.println("Account Type: " + account.getAccountType());
        System.out.println("Customer Type: " + account.getCustomer().getCustomerType());
        System.out.println("Initial Deposit: ₹" + account.getBalance());

        if (account.getAccountType().equalsIgnoreCase("Savings") && interestCalculator != null) {
            double interest = interestCalculator.calculateInterest(1);
            System.out.println("Estimated Default Interest (1 year @ 4%): ₹" + interest);
        }

        if (account.getAccountType().equalsIgnoreCase("Current")) {
            if (account.getCustomer().getCustomerType().equalsIgnoreCase("Business") && account.getBusinessProof() != null) {
                System.out.println("Business Proof: " + account.getBusinessProof());
            }
        }
    }

}
