package com.bankaccountsetup.Service;

import com.bankaccountsetup.Exception.*;
import com.bankaccountsetup.Interface.*;
import com.bankaccountsetup.models.AccountDetails;

import java.util.Random;

public class SavingsAccountService implements AccountCreationService, InterestCalculation, AmountWithdrawService {

    private AccountDetails account;
    private int withdrawalCount = 0;
    private static final int MAX_WITHDRAWALS = 3;
    private static final double INTEREST_RATE = 0.04;

    public SavingsAccountService(AccountDetails account) {
        this.account = account;
    }

    @Override
    public String openAccount() throws InvalidAccountException {
        if (!account.getCustomer().getCustomerType().equalsIgnoreCase("Individual")) {
            throw new InvalidAccountException("❌ Savings account can only be opened by individuals.");
        }

        if (account.getBalance() < 1000) {
            throw new InvalidAccountException("❌ Minimum deposit for a Savings Account is ₹1000.");
        }

        Random rand = new Random();
        int randomNum = 100000 + rand.nextInt(900000);
        String accountId = "SAV" + randomNum;

        System.out.println("✅ Savings Account opened successfully. Account ID: " + accountId);
        return accountId;
    }

    @Override
    public String withdraw(double amount) throws InsufficientFundsException {
        if (withdrawalCount >= MAX_WITHDRAWALS) {
            throw new InsufficientFundsException("❌ Withdrawal limit of 3 per month reached.");
        }

        if (amount <= 0) {
            throw new InsufficientFundsException("❌ Withdrawal amount must be greater than ₹0.");
        }

        double currentBalance = account.getBalance();
        if (amount > currentBalance) {
            throw new InsufficientFundsException("❌ Insufficient balance.");
        }

        account.setBalance(currentBalance - amount);
        withdrawalCount++;

        return "✅ Withdrawal of ₹" + amount + " successful. 💰 Updated balance: ₹" + account.getBalance();
    }

    @Override
    public double calculateInterest(int year) {
        if (year <= 0) {
            System.out.println("⚠️ Year must be greater than 0.");
            return 0.0;
        }
        return account.getBalance() * INTEREST_RATE * year;
    }

}
