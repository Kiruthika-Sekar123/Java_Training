package com.telecomcustomersystem.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.telecomcustomersystem.Model.Customer;

public class CustomerService {
	 
	
	 private List<Customer> customers = new ArrayList<>();
	 Random rand = new Random();
	private SubscribeService subscribeService;
	private FileComplaintService complaintService;
	
	 public CustomerService(SubscribeService subscribeService, FileComplaintService complaintService) {
		        this.subscribeService=subscribeService;
		        this.complaintService=complaintService;
			}

	public List<Customer> addCustomer(Customer newCustomer) {
	        newCustomer.setCustomerId(rand.nextInt(10000)); 
	        customers.add(newCustomer);
	        return customers;
	    }
	 
	 public void showAllCustomerDetails() {
		    if(customers.isEmpty()) {
		        System.out.println("No customers registered.");
		        return;
		    }
		    
		    for (Customer c : customers) {
		        System.out.println("Name: " + c.getName());
		        System.out.println("Phone Number: " + c.getPhoneNumber());
		        System.out.println("Address Proof: " + c.getAddressProof());
		        
		        String PhoneNumber = c.getPhoneNumber();
		        
		        // Show subscribed services
		        List<String> services = subscribeService.viewSubscribedServices(PhoneNumber);
		        if (services == null || services.isEmpty()) {
		            System.out.println("Subscribed Services: None");
		        } else {
		            System.out.println("Subscribed Services: " + String.join(", ", services));
		        }
		        
		        // Show complaints
		        List<String> complaints = complaintService.getComplaints(PhoneNumber);
		        if (complaints == null || complaints.isEmpty()) {
		            System.out.println("Complaints: None");
		        } else {
		            System.out.println("Complaints:");
		            for (String comp : complaints) {
		                System.out.println(" - " + comp);
		            }
		        }
		        
		        System.out.println("-------------------------");
		    }
		}


	    public void subscribeService(String phonenumber, String service) {
	    	 if(customers.isEmpty()) {
			        System.out.println("No customers registered.");
			        return;
			    }
	        subscribeService.subscribeService(phonenumber, service);
	    }
	    
	    public void unsubscribeService(String phonenumber, String service) {
	    	 if(customers.isEmpty()) {
			        System.out.println("No customers registered.");
			        return;
			    }
	        subscribeService.unsubscribeService(phonenumber, service);
	    }

	    public List<String> getSubscribedServices(String phoneNumber) {
	    	 if(customers.isEmpty()) {
			        System.out.println("No customers registered.");
			        
			    }
	        return subscribeService.viewSubscribedServices(phoneNumber);
	    }


	    public void fileComplaint(String phonenumber, String complaint) {
	    	 if(customers.isEmpty()) {
			        System.out.println("No customers registered.");
			        return;
			    }
	        complaintService.addComplaint(phonenumber,complaint);
	    }

	    public void getComplaints(String phonenumber) {
	        complaintService.getComplaints(phonenumber);
	    }

		
	
}
