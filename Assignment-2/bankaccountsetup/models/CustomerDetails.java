package com.bankaccountsetup.models;

public class CustomerDetails {
    private String fullName;
    private String phoneNumber;
    private String panNumber;
    private String aadhaarNumber;
    private String CustomerType;

    // Constructor
    public CustomerDetails(String fullName, String phoneNumber, String panNumber, String aadhaarNumber, String CustomerType) {
        this.fullName = fullName;
        this.phoneNumber = phoneNumber;
        this.panNumber = panNumber;
        this.aadhaarNumber = aadhaarNumber;
        this.CustomerType=CustomerType;
    }

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getPanNumber() {
		return panNumber;
	}

	public void setPanNumber(String panNumber) {
		this.panNumber = panNumber;
	}

	public String getAadhaarNumber() {
		return aadhaarNumber;
	}

	public void setAadhaarNumber(String aadhaarNumber) {
		this.aadhaarNumber = aadhaarNumber;
	}

	public String getCustomerType() {
		return CustomerType;
	}

	public void setCustomerType(String customerType) {
		CustomerType = customerType;
	}

    
}    