package com.telecomcustomersystem.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.telecomcustomersystem.Model.Customer;

public class FileComplaintService {
	 private Map<String, Customer> customerMap;

	    public FileComplaintService() {
	        this.customerMap = new HashMap<>();
	    }

	    
	    public void addCustomer(Customer customer) {
	        customerMap.put(customer.getPhoneNumber(), customer);
	    }
	    
    public void addComplaint(String phoneNumber, String complaint) {
        Customer customer = customerMap.get(phoneNumber);

        if (customer != null) {
            
            customer.addComplaint(complaint);
            System.out.println("Customer " + customer.getName() + " successfully complaint to " + complaint);
        } else {
           
            System.out.println("Customer with phone number " + phoneNumber + " not found.");
        }
    }
    
    public List<String> getComplaints(String phoneNumber) {
        System.out.println("Looking up customer with phone number: " + phoneNumber);
        Customer customer = customerMap.get(phoneNumber);

        if (customer != null) {
            System.out.println("Customer found: " + customer.getName());
            System.out.println("complaint services: " + customer.getComplaints());
            return new ArrayList<>(customer.getComplaints());
        } else {
            System.out.println("Customer with phone number " + phoneNumber + " not found.");
            return new ArrayList<>();  // Return empty list if no customer found
        }
    }

}
