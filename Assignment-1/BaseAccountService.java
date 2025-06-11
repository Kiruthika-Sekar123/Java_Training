package com.bankaccountsetup;

public abstract class BaseAccountService implements AccountCreationService {
     String customerName;
     int initialdeposit;
     String panNumber;
     String aadhaarNumber;
     String branch;
     String accountType;
     String businessProof;
     String PhoneNumber;

    public BaseAccountService(String customerName, int initialdeposit, String panNumber,
                                  String aadhaarNumber, String branch, String accountType, String PhoneNumber, String businessProof) {
        this.customerName = customerName;
        this.initialdeposit = initialdeposit;
        this.panNumber = panNumber;
        this.aadhaarNumber = aadhaarNumber;
        this.branch = branch;
        this.accountType = accountType;
        this.businessProof = businessProof;
        this.PhoneNumber = PhoneNumber;
    }

    private boolean isInvalid(String s) {
        return s == null || s.trim().isEmpty() || s.equalsIgnoreCase("null");
    }

    @Override
    public boolean validateAccountDetails() {
        if (isInvalid(customerName) || initialdeposit <= 0 || isInvalid(panNumber) || 
            isInvalid(aadhaarNumber) || isInvalid(branch) || isInvalid(accountType) || 
            isInvalid(PhoneNumber)) {
            return false;
        }

        if (accountType.equalsIgnoreCase("Current") && isInvalid(businessProof)) {
            return false;
        }

        return true;
    }

    @Override
    public String createAccount() {
        if (validateAccountDetails()) {
            String accountId = openAccount(); // capture account ID
            return accountId;
        } else {
            System.out.println("Account validation failed.");
            return null;
        }
    }

    public void displayUserDetails(String accountid) {
        System.out.println("\n--- Account Holder Details ---");
        System.out.println("Name: " + customerName);
        System.out.println("accountNumber: " + accountid);
        System.out.println("Phone Number: " + PhoneNumber);
        System.out.println("PAN Number: " + panNumber);
        System.out.println("Aadhaar Number: " + aadhaarNumber);
        System.out.println("Branch: " + branch);
        System.out.println("Account Type: " + accountType);
        System.out.println("Initial Deposit: ₹" + initialdeposit);
        
        if (accountType.equalsIgnoreCase("Current")) {
            System.out.println("Business Proof: " + businessProof);
        }
    }


    public abstract String openAccount();

}
