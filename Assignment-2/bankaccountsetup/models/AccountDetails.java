package com.bankaccountsetup.models;

public class AccountDetails{
    private CustomerDetails customer;
    private double balance;
    private String branch;
    private String accountType;
    private String businessProof;

    public AccountDetails(CustomerDetails customer, double initialDeposit, String branch, String accountType, String businessProof) {
        this.customer = customer;
        this.balance = initialDeposit;  // initialize balance with deposit
        this.branch = branch;
        this.accountType = accountType;
        this.businessProof = businessProof;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

	public String getBranch() {
		return branch;
	}

	public void setBranch(String branch) {
		this.branch = branch;
	}

	public String getAccountType() {
		return accountType;
	}

	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}

	public String getBusinessProof() {
		return businessProof;
	}

	public void setBusinessProof(String businessProof) {
		this.businessProof = businessProof;
	}

	public CustomerDetails getCustomer() {
		return customer;
	}

	public void setCustomer(CustomerDetails customer) {
		this.customer = customer;
	}

}

