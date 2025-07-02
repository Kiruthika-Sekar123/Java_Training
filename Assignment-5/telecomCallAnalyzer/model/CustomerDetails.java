package com.telecomCallAnalyzer.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class CustomerDetails {
     private String CustomerId;
     private String name;
     private String PhoneNumber;
     private String Operator;
     private String Location;
     private List<CallRecord> CallRecord;

     public CustomerDetails(String name, String PhoneNumber, String Operator, String Location){
         this.CustomerId = UUID.randomUUID().toString(); // generates unique ID
         this.name=name;
         this.PhoneNumber = PhoneNumber;
         this.Operator=Operator;
         this.Location = Location;
         this.CallRecord = new ArrayList<>();
     }

    public CustomerDetails() {

    }

    public String getCustomerId() {
        return CustomerId;
    }

    public void setCustomerId(String customerId) {
        CustomerId = customerId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNumber() {
        return PhoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        PhoneNumber = phoneNumber;
    }

    public List<CallRecord> getCallRecord() {
        return CallRecord;
    }

    public void setCallRecord(CallRecord callRecord) {
        CallRecord.add(callRecord);
    }

    public String getOperator() {
        return Operator;
    }

    public void setOperator(String operator) {
        Operator = operator;
    }

    public String getLocation() {
        return Location;
    }

    public void setLocation(String location) {
        Location = location;
    }

    @Override
    public String toString() {
        return "CustomerDetails{" +
                "name='" + name + '\'' +
                ", PhoneNumber='" + PhoneNumber + '\'' +
                ", CallRecord=" + CallRecord +
                '}';
    }
}
