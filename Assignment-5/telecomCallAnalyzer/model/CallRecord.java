package com.telecomCallAnalyzer.model;

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

    public long getDurationInSeconds() {
        return durationInSeconds;
    }

    public void setDurationInSeconds(long durationInSeconds) {
        this.durationInSeconds = durationInSeconds;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}

