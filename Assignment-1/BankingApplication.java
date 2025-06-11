package com.bankaccountsetup;

import java.util.Scanner;

public class BankingApplication {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\nWelcome to ICIC Bank Account Setup");

            // Collect all inputs first
            System.out.print("Enter your full name: ");
            String customerName = scanner.nextLine().trim();

            System.out.print("Enter your initial deposit: ");
            int initialdeposit = scanner.nextInt();
            scanner.nextLine();

            if (initialdeposit < 500) {
                System.out.println("Minimum balance must be at least ₹500");
                continue;
            }

            System.out.print("Enter your Phone Number: ");
            String phoneNumber = scanner.nextLine().trim();

            System.out.print("Enter your PAN card number: ");
            String panNumber = scanner.nextLine().trim();

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

            // Validate required fields
            if (customerName.isEmpty() || panNumber.isEmpty() || aadhaarNumber.isEmpty()
                    || branch.isEmpty() || accountType.isEmpty() || phoneNumber.isEmpty()) {
                System.out.println("One or more required fields are missing. Please try again.");
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

            // Confirm before proceeding
            System.out.print("Do you want to create the account with the entered details? (yes/no): ");
            String confirmation = scanner.nextLine().trim();
            if (!confirmation.equalsIgnoreCase("yes")) {
                System.out.println("Account creation cancelled.");
                continue;
            }

            if (accountType.equalsIgnoreCase("Savings")) {
                AccountCreationService account = new SavingsAccountService(
                        customerName,
                        initialdeposit,
                        panNumber,
                        aadhaarNumber,
                        branch,
                        accountType,
                        phoneNumber,
                        businessProof
                );

                if (!account.validateAccountDetails()) {
                    System.out.println("Account validation failed.");
                    continue; // go back to the start of the loop
                }

                String accountId = account.createAccount();
                account.displayUserDetails();
                System.out.println("Notification: Savings Account " + accountId + " created for " + customerName + " in " + branch + " branch");

            } else {
                AccountCreationService account = new CurrentAccountService(
                        customerName,
                        initialdeposit,
                        panNumber,
                        aadhaarNumber,
                        branch,
                        accountType,
                        phoneNumber,
                        businessProof
                );

                if (!account.validateAccountDetails()) {
                    System.out.println("Account validation failed.");
                    continue; // go back to the start
                }

                String accountId = account.createAccount();
                account.displayUserDetails();
                System.out.println("Notification: Current Account " + accountId + " created for " + customerName + " in " + branch + " branch");
            }


            // Ask if the user wants to create another account
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
