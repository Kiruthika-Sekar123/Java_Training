package com.telecomcustomersystem.Model;


public class CallRecord {
    private String customerName;
    private String phoneNumber;
    private long durationInSeconds;

    public CallRecord(String customerName, String phoneNumber, Long durationInSeconds) {
        this.customerName = customerName;
        this.phoneNumber = phoneNumber;
        this.durationInSeconds = durationInSeconds;
    }

    public String toString() {
        return "Customer: " + customerName + ", Called: " + phoneNumber + ", Duration: " + durationInSeconds + " seconds";
    }
}
