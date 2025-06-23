package com.telecomcustomersystem.Service;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import com.telecomcustomersystem.Model.CallRecord;
import com.telecomcustomersystem.Model.Customer;

public class CallService {
    private Map<String, Customer> customerMap = new HashMap<>();
    private Customer currentCustomer;
    private LinkedList<CallRecord> callHistory ;
    private Long startTime;
    private Long duration;

    // Constructor
    public CallService(Customer currentCustomer) {
        this.currentCustomer = currentCustomer;
        this.callHistory= new LinkedList<>();
    }

    public void addCustomer(Customer customer) {
        customerMap.put(customer.getPhoneNumber(), customer);
    }

    public void showAllCustomerDetails() {
        if (customerMap.isEmpty()) {
            System.out.println("No customers registered.");
        } else {
            customerMap.forEach((phone, customer) -> {
                System.out.println("Customer Name: " + customer.getName());
                System.out.println("Phone: " + customer.getPhoneNumber());
                System.out.println("Address Proof: " + customer.getAddressProof());
            });
        }
    }

    public void subscribeService(String phoneNumber, String service) {
        Customer customer = customerMap.get(phoneNumber);
        if (customer != null) {
            customer.addSubscribedService(service);
            System.out.println("Service " + service + " subscribed to " + customer.getName());
        } else {
            System.out.println("Customer not found.");
        }
    }

    public void fileComplaint(String phoneNumber, String complaint) {
        Customer customer = customerMap.get(phoneNumber);
        if (customer != null) {
            customer.addComplaint(complaint);
            System.out.println("Complaint filed successfully.");
        } else {
            System.out.println("Customer not found.");
        }
    }

    public void getComplaints(String phoneNumber) {
        Customer customer = customerMap.get(phoneNumber);
        if (customer != null) {
            System.out.println("Complaints for " + customer.getName() + ":");
            customer.getComplaints().forEach(System.out::println);
        } else {
            System.out.println("Customer not found.");
        }
    }

    public void showCustomerDetails(String phoneNumber) {
        Customer customer = customerMap.get(phoneNumber);
        if (customer != null) {
            System.out.println("Name: " + customer.getName());
            System.out.println("Phone: " + customer.getPhoneNumber());
            System.out.println("Address Proof: " + customer.getAddressProof());
            System.out.println("Subscribed Services: " + customer.getSubscribedServices());
            System.out.println("Complaints: " + customer.getComplaints());
        } else {
            System.out.println("Customer not found.");
        }
    }

    // Method to simulate an outgoing call
    public void outGoingCall(String phoneNumber) {
        System.out.println("Making an outgoing call to " + phoneNumber);
        startTime = System.currentTimeMillis();
        System.out.println("Call made successfully.");
    }

    // Overloaded method to simulate cutting the call
    public boolean outGoingCall(String phoneNumber, String opt) {
        if (opt.equalsIgnoreCase("Yes")) {
            // Calculate duration in seconds
            duration = (System.currentTimeMillis() - startTime) / 1000; // Duration in seconds
            CallRecord record = new CallRecord(currentCustomer.getName(), phoneNumber, duration);
            addCallHistory(record);
            System.out.println("Call to " + phoneNumber + " has been cut. Duration: " + duration + " seconds.");
            return true;
        }
        return false;
    }

    // Method to add a call record to call history
    public void addCallHistory(CallRecord record) {
        callHistory.addFirst(record);
    }

    // Method to get the call history
    public List<CallRecord> getCallHistory() {
        return callHistory;
    }
}
