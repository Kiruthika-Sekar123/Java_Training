package com.bankaccountsetup;

import java.util.Scanner;

import com.bankaccountsetup.Exception.*;
import com.bankaccountsetup.Interface.*;
import com.bankaccountsetup.Service.*;
import com.bankaccountsetup.models.*;
import com.bankaccountsetup.util.*;

public class BankingApplication {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        AccountCreationService accountService = null;
        AmountWithdrawService withdrawService = null;
        InterestCalculation interestCalculation = null;
        AccountDetails account = null;
        String accountId = null;

        while (true) {
            System.out.println("\n===== ICIC BANK MAIN MENU =====");
            System.out.println("1. Create New Account");
            System.out.println("2. Show Account Details");
            System.out.println("3. Withdraw");
            System.out.println("4. Calculate Interest");
            System.out.println("5. Exit");
            System.out.print("Enter your choice (1-5): ");
            String menuChoice = scanner.nextLine().trim();

            switch (menuChoice) {
                case "1":
                    try {
                        System.out.print("Enter your full name: ");
                        String customerName = scanner.nextLine().trim();
                        if (customerName.isEmpty()) {
                            throw new InvalidAccountException("Name cannot be empty.");
                        }

                        System.out.print("Enter your Phone Number: ");
                        String phoneNumber = scanner.nextLine().trim();
                        if (!InputValidationService.isValidPhone(phoneNumber)) {
                            throw new InvalidAccountException("Phone number must be a 10-digit numeric value.");
                        }

                        System.out.print("Enter your PAN card number: ");
                        String panNumber = scanner.nextLine().trim().toUpperCase();
                        if (!InputValidationService.isValidPan(panNumber)) {
                            throw new InvalidAccountException("PAN must be 10 characters in the format: 5 letters, 4 digits, 1 letter.");
                        }

                        System.out.print("Enter your Aadhaar number: ");
                        String aadhaarNumber = scanner.nextLine().trim();
                        if (!InputValidationService.isValidAadhaar(aadhaarNumber)) {
                            throw new InvalidAccountException("Aadhaar number must be a 12-digit numeric value.");
                        }

                        System.out.print("Enter the branch name: ");
                        String branch = scanner.nextLine().trim();
                        if (branch.isEmpty()) {
                            throw new InvalidAccountException("Branch name cannot be empty.");
                        }

                        System.out.print("Enter account type (Savings/Current): ");
                        String accountType = scanner.nextLine().trim();
                        if (!InputValidationService.isValidAccountType(accountType)) {
                            throw new InvalidAccountException("Invalid account type. Must be Savings or Current.");
                        }

                        System.out.print("Enter customer type (Individual/Business): ");
                        String customerType = scanner.nextLine().trim();
                        if (!InputValidationService.isValidCustomerForAccountType(accountType, customerType)) {
                            throw new InvalidAccountException("Invalid customer type for selected account type.");
                        }

                        System.out.print("Enter your initial deposit: ");
                        while (!scanner.hasNextInt()) {
                            System.out.println("Please enter a valid numeric amount.");
                            scanner.next();
                        }
                        int initialDeposit = scanner.nextInt();
                        scanner.nextLine();

                        if (!InputValidationService.isValidDeposit(initialDeposit, accountType)) {
                            throw new InvalidAccountException("Minimum deposit not met for the account type.");
                        }

                        String businessProof = null;
                        if (accountType.equalsIgnoreCase("Current") && customerType.equalsIgnoreCase("Business")) {
                            System.out.print("Enter business proof (e.g. Shop License): ");
                            businessProof = scanner.nextLine().trim();
                            if (!InputValidationService.isBusinessProofValid(accountType, customerType, businessProof)) {
                                throw new InvalidAccountException("Business proof is required for a Current Business account.");
                            }
                        }

                        CustomerDetails customer = new CustomerDetails(customerName, phoneNumber, panNumber, aadhaarNumber, customerType);
                        account = new AccountDetails(customer, initialDeposit, branch, accountType, businessProof);

                        if (accountType.equalsIgnoreCase("Savings")) {
                            SavingsAccountService savingsService = new SavingsAccountService(account);
                            accountService = savingsService;
                            withdrawService = savingsService;
                            interestCalculation = savingsService;
                        } else {
                            CurrentAccountService currentService = new CurrentAccountService(account);
                            accountService = currentService;
                            withdrawService = currentService;
                            interestCalculation = null;
                        }

                        accountId = accountService.openAccount();
                        if(accountId != null) {
                        	System.out.println("Account validation Completed");
                        }
                        System.out.println("✅ Activate account successfully! ID: " + accountId);

                    } catch (InvalidAccountException e) {
                        System.out.println("❌ Account creation failed: " + e.getMessage());
                    }
                    break;

                case "2":
                    if (account == null || accountService == null) {
                        System.out.println("⚠️ No account found. Please create an account first.");
                    } else {
                        AccountDisplayUtil.displayAccountDetails(accountId, account, interestCalculation);
                    }
                    break;

                case "3":
                    if (account == null || withdrawService == null) {
                        System.out.println("⚠️ No account found. Please create an account first.");
                        break;
                    }
                    System.out.print("Enter amount to withdraw: ");
                    while (!scanner.hasNextDouble()) {
                        System.out.println("Please enter a valid amount.");
                        scanner.next();
                    }
                    double amount = scanner.nextDouble();
                    scanner.nextLine();

                    try {
                        String result = withdrawService.withdraw(amount);
                        System.out.println(result);
                    } catch (InsufficientFundsException e) {
                        System.out.println("❌ Withdrawal failed: " + e.getMessage());
                    }
                    break;

                case "4":
                    if (account == null || interestCalculation == null) {
                        System.out.println("❌ Interest calculation is available only for Savings accounts or Current accounts after account creation.");
                        break;
                    }
                    System.out.print("Enter number of years: ");
                    while (!scanner.hasNextInt()) {
                        System.out.println("Please enter a valid number.");
                        scanner.next();
                    }
                    int years = scanner.nextInt();
                    scanner.nextLine();

                    double interest = interestCalculation.calculateInterest(years);
                    System.out.println("💰 Interest for " + years + " year(s): ₹" + interest);
                    break;

                case "5":
                    System.out.println("👋 Thank you for banking with us. Goodbye!");
                    scanner.close();
                    return;

                default:
                    System.out.println("❌ Invalid choice. Please enter 1 to 5.");
            }
        }
    }
}
