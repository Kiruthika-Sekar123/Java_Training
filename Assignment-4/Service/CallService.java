package com.TelecomSystem.Service;

import java.util.LinkedList;
import com.TelecomSystem.Model.CallRecord;
import com.TelecomSystem.Model.Customer;

public class CallService {
    private LinkedList<CallRecord> callHistory = new LinkedList<>();
    private long startTime;
    private Customer customer;

    public CallService(Customer customer) {
        this.customer = customer;
    }

    public void outGoingCall(String phoneNumber) {
        System.out.println("Calling " + phoneNumber + "...");
        startTime = System.currentTimeMillis();
    }

    public boolean outGoingCall(String phoneNumber, String opt) {
        if (opt.equalsIgnoreCase("yes")) {
            long endTime = System.currentTimeMillis();
            long durationMillis = endTime - startTime;
            long durationSeconds = durationMillis / 1000;

            System.out.println("Call to " + phoneNumber + " ended.");
            System.out.println("Duration: " + durationSeconds + " seconds");

            // ✅ Store in call history
            storeCallHistory(phoneNumber, durationSeconds);
            return true;
        } else {
            return false;
        }
    }

    private void storeCallHistory(String phoneNumber, long durationSeconds) {
        CallRecord record = new CallRecord(customer.getName(), phoneNumber, durationSeconds);
        callHistory.addFirst(record);
        
    }

    public LinkedList<CallRecord> getCallHistory() {
        return callHistory;
    }
}
