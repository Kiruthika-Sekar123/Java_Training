package com.telecom.model;


public class CallLog {
    private Customer caller;
    private Customer receiver;
    private long startTime;
    private long endTime;

    public CallLog(Customer caller, Customer receiver) {
        this.caller = caller;
        this.receiver = receiver;
    }

    public Customer getCaller() { return caller; }
    public Customer getReceiver() { return receiver; }
    public long getStartTime() { return startTime; }
    public long getEndTime() { return endTime; }

    public void startCall() {
        this.startTime = System.currentTimeMillis();
    }

    public void endCall() {
        this.endTime = System.currentTimeMillis();
    }

    public long getDurationInSeconds() {
        return (endTime - startTime) / 1000;
    }
}

