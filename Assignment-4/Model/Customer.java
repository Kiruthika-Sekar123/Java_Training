package com.TelecomSystem.Model;


public class Customer {
	
	private int CustomerId;
	private String Name;
	private String Number;
	private String Addressproof;
	
	public Customer(String name, String number, String addressproof) {
		super();
		this.Name = name;
		this.Number = number;
		this.Addressproof = addressproof;
	}
	
	public int getCustomerId() {
		return CustomerId;
	}

	public void setCustomerId(int CustomerId) {
		this.CustomerId = CustomerId;
	}

	public String getName() {
		return Name;
	}

	public void setName(String name) {
		this.Name = name;
	}

	public String getNumber() {
		return Number;
	}

	public void setNumber(String number) {
		this.Number = number;
	}

	public String getAddressproof() {
		return Addressproof;
	}

	public void setAddressproof(String addressproof) {
		this.Addressproof = addressproof;
	}	
	
	@Override
	public String toString() {
		return "Customer [CustomerId=" + CustomerId + ", Name=" + Name + ", Number=" + Number + ", Addressproof="
				+ Addressproof + "]";
	}

	
}