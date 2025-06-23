package com.TelecomSystem.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import com.TelecomSystem.Model.Customer;

public class CustomerService {
	 
	 private SubscribeService subscribeService = new SubscribeService();
	 private FileComplaintService complaintService = new FileComplaintService();

	 private List<Customer> customers = new ArrayList<>();
	 Random rand = new Random();
	
	 public List<Customer> addCustomer(Customer newCustomer) {
	        newCustomer.setCustomerId(rand.nextInt(10000)); 
	        customers.add(newCustomer);
	        return customers;
	    }
	 
	 public void showCustomerDetails(){
		 System.out.println("-------------Show Customer Details-----------");
		 for (Customer c : customers) {
			System.out.println(c);
			
		}
		System.out.println("----------------------------");
	 }


	    public void subscribeService(String service) {
	        subscribeService.subscribeService(service);
	    }

	    public List<String> getSubscribedServices() {
	        return subscribeService.getSubscribedServices();
	    }


	    public void fileComplaint(String complaint) {
	        complaintService.addComplaint(complaint);
	    }

	    public List<String> getComplaints() {
	        return complaintService.getComplaints();
	    }
	
}
