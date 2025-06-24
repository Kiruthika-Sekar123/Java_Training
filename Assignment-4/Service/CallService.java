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
