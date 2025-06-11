package com.bankaccountsetup;

public class SavingsAccountService  extends BaseAccountService{
	
	 public SavingsAccountService(String customerName, int initialdeposit, String panNumber,
             String aadhaarNumber, String branch, String accountType, String PhoneNumber,
             String businessProof) {
super(customerName, initialdeposit, panNumber, aadhaarNumber, branch, accountType, PhoneNumber, businessProof);
}
	 @Override
	public String openAccount() {
	     String accountId = "SAV" + System.currentTimeMillis(); // Simulated account number
	     System.out.println("Saving Account opened for: " + customerName + " | Account ID: " + accountId);
	     return accountId;
	 }
	 
}
