package com.mavenproject;

import java.util.ArrayList;
import java.util.List;

class CustomerDetails{
	String name;
	String phonenumber;
	
	
	public CustomerDetails(String name, String phonenumber) {
		super();
		this.name = name;
		this.phonenumber = phonenumber;
	}


	@Override
	public String toString() {
		return "CustomerDetails [name=" + name + ", phonenumber=" + phonenumber + "]";
	}	
	
}
public class Telecom {
   
	List<CustomerDetails> user= new ArrayList<>();
	public boolean addCustomer(CustomerDetails customer) {
		user.add(customer);
		return true;
	}
	
	public void display() {
		user.forEach(System.out::println);
	}
}
