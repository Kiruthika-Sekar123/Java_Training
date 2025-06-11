package com.bankaccountsetup;

public class CurrentAccountService extends BaseAccountService {
	
	 public CurrentAccountService(String customerName, int initialdeposit, String panNumber,
             String aadhaarNumber, String branch, String accountType, String PhoneNumber,
             String businessProof) {
super(customerName, initialdeposit, panNumber, aadhaarNumber, branch, accountType,PhoneNumber, businessProof);
}
	
	 @Override
	 public String openAccount() {
	     String accountId = "CUR" + System.currentTimeMillis(); // Simulated account number
	     System.out.println("Current Account opened for: " + customerName + " | Account ID: " + accountId);
	     return accountId;
	 }

}
