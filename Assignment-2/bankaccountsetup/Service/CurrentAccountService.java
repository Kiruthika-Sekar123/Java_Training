package com.bankaccountsetup.Service;

import com.bankaccountsetup.Exception.*;
import com.bankaccountsetup.Interface.*;
import com.bankaccountsetup.models.AccountDetails;
import java.util.Random;

public class CurrentAccountService implements AccountCreationService, AmountWithdrawService {
	 
	 private AccountDetails account;
	 public CurrentAccountService(AccountDetails account ) {
		 this.account = account;
	}


	  @Override
	    public String openAccount() throws InvalidAccountException {
	        // Validate minimum deposit
	        if (account.getBalance() < 5000) {
	            throw new InvalidAccountException("Minimum deposit for a Current Account is ₹5000.");
	        }

	        // Validate customer type
	        String customerType = account.getCustomer().getCustomerType();
	        if (!customerType.equalsIgnoreCase("Individual") && !customerType.equalsIgnoreCase("Business")) {
	            throw new InvalidAccountException("Current Account can only be opened by Individuals or Businesses.");
	        }

	        // Generate account ID
	        Random rand = new Random();
	        int randomNum = 100000 + rand.nextInt(900000);
	        String accountId = "CUR" + randomNum;

	        System.out.println("Current Account opened successfully. Account ID: " + accountId);
	        return accountId;
	    }
	 

	 
	     @Override
	     public String withdraw(double amount) throws InsufficientFundsException {
	         if (amount <= 0) {
	             throw new InsufficientFundsException("Withdrawal amount must be greater than ₹0.");
	         }

	         double currentBalance = account.getBalance();

	         if (amount > currentBalance) {
	             throw new InsufficientFundsException("❌ Insufficient balance. You tried to withdraw ₹" + amount + " but have only ₹" + currentBalance + ".");
	         }

	         double remainingBalance = currentBalance - amount;

	         // Apply penalty if balance drops below ₹2000
	         if (remainingBalance < 2000) {
	             if (remainingBalance < 500) {
	                 throw new InsufficientFundsException("❌ Cannot apply ₹500 penalty due to insufficient funds (Remaining: ₹" + remainingBalance + ").");
	             }
	             remainingBalance -= 500;
	             account.setBalance(remainingBalance);
	             return "⚠️ Penalty of ₹500 applied (balance fell below ₹2000).\n✅ Withdrawal successful. Updated balance: ₹" + remainingBalance;
	         }

	         // No penalty needed
	         account.setBalance(remainingBalance);
	         return "✅ Withdrawal of ₹" + amount + " successful. Updated balance: ₹" + remainingBalance;
	     }


}
