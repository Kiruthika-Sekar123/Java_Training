package com.bankaccountsetup;

import java.util.Scanner;

public class BankingApplication {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\nWelcome to ICIC Bank Account Setup");

            System.out.print("Enter your full name: ");
            String customerName = scanner.nextLine().trim();

            System.out.print("Enter your initial deposit: ");
            int initialdeposit = scanner.nextInt();
            scanner.nextLine(); // consume leftover newline

            if (initialdeposit < 500) {
                System.out.println("Minimum balance must be at least ₹500");
                continue;
            }

            System.out.print("Enter your Phone Number: ");
            String phoneNumber = scanner.nextLine().trim();

            System.out.print("Enter your PAN card number: ");
            String panNumber = scanner.nextLine().trim().toUpperCase();

            System.out.print("Enter your Aadhaar number: ");
            String aadhaarNumber = scanner.nextLine().trim();

            System.out.print("Enter the branch name: ");
            String branch = scanner.nextLine().trim();

            System.out.print("Enter account type (Savings/Current): ");
            String accountType = scanner.nextLine().trim();

            String businessProof = null;
            if (accountType.equalsIgnoreCase("Current")) {
                System.out.print("Enter business proof (e.g. Shop License): ");
                businessProof = scanner.nextLine().trim();
            }

            // Required field checks
            if (customerName.isEmpty() || panNumber.isEmpty() || aadhaarNumber.isEmpty()
                    || branch.isEmpty() || accountType.isEmpty() || phoneNumber.isEmpty()) {
                System.out.println("One or more required fields are missing. Please try again.");
                continue;
            }

            // Phone number: must be 10 digits numeric
            if (!phoneNumber.matches("\\d{10}")) {
                System.out.println("Phone number must be a 10-digit numeric value.");
                continue;
            }

            // Aadhaar number: must be 12 digits numeric
            if (!aadhaarNumber.matches("\\d{12}")) {
                System.out.println("Aadhaar number must be a 12-digit numeric value.");
                continue;
            }

            // PAN: must be 5 letters, 4 digits, 1 letter
            if (!panNumber.matches("[A-Z]{5}[0-9]{4}[A-Z]")) {
                System.out.println("PAN number must be 10 characters in the format: 5 letters, 4 digits, 1 letter.");
                continue;
            }

            if (!accountType.equalsIgnoreCase("Savings") && !accountType.equalsIgnoreCase("Current")) {
                System.out.println("Invalid account type selected. Please try again.");
                continue;
            }

            if (accountType.equalsIgnoreCase("Current") && (businessProof == null || businessProof.isEmpty())) {
                System.out.println("Business proof is required for a Current account. Please try again.");
                continue;
            }

            System.out.print("Do you want to create the account with the entered details? (yes/no): ");
            String confirmation = scanner.nextLine().trim();
            if (!confirmation.equalsIgnoreCase("yes")) {
                System.out.println("Account creation cancelled.");
                continue;
            }

            AccountCreationService account;
            if (accountType.equalsIgnoreCase("Savings")) {
                account = new SavingsAccountService(
                        customerName,
                        initialdeposit,
                        panNumber,
                        aadhaarNumber,
                        branch,
                        accountType,
                        phoneNumber,
                        businessProof
                );
            } else {
                account = new CurrentAccountService(
                        customerName,
                        initialdeposit,
                        panNumber,
                        aadhaarNumber,
                        branch,
                        accountType,
                        phoneNumber,
                        businessProof
                );
            }

            if (!account.validateAccountDetails()) {
                System.out.println("Account validation failed.");
                continue;
            }

            String accountId = account.createAccount();
            account.displayUserDetails(accountId);

            System.out.print("\nDo you want to create another account? (yes/no): ");
            String continueChoice = scanner.nextLine().trim();
            if (!continueChoice.equalsIgnoreCase("yes")) {
                System.out.println("Thank you for using our service. Goodbye!");
                break;
            }
        }

        scanner.close();
    }
}
