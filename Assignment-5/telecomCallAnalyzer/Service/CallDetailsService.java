package com.telecomCallAnalyzer.Service;

import com.telecomCallAnalyzer.model.CallRecord;
import com.telecomCallAnalyzer.model.CustomerDetails;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

public class CallDetailsService {

    // Map of phone number to CustomerDetails
    private Map<String, CustomerDetails> customers = new HashMap<>();

    // Register a customer
    public void addCustomer(CustomerDetails customerDetails) {
        String phone = customerDetails.getPhoneNumber();
        if (customers.containsKey(phone)) {
            System.out.println("Customer with phone number " + phone + " already exists.");
        } else {
            customers.put(phone, customerDetails);
            System.out.println("Customer registered with ID: " + customerDetails.getCustomerId());
        }
    }

    // Make a call from caller to receiver
    public void makeCall(CustomerDetails caller, CustomerDetails receiver) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Starting call from " + caller.getPhoneNumber() + " to " + receiver.getPhoneNumber() + "...");
        LocalDateTime startTime = LocalDateTime.now();
        System.out.println("Call started at: " + startTime);
        System.out.println("Call in progress. Type 'cut' to end the call.");

        while (true) {
            String userInput = scanner.nextLine();
            if (userInput.equalsIgnoreCase("cut")) {
                break;
            } else {
                System.out.println("Invalid input. Type 'cut' to end the call.");
            }
        }

        LocalDateTime endTime = LocalDateTime.now();
        Duration duration = Duration.between(startTime, endTime);
        long seconds = duration.getSeconds();

        CallRecord record = new CallRecord(caller.getPhoneNumber(), receiver.getPhoneNumber(), seconds);

        // Add call record to caller's history
        caller.setCallRecord(record);

        System.out.println("Call ended at: " + endTime);
        System.out.println("Call duration: " + seconds + " seconds.");
    }

    // Get customer by phone number
    public Optional<CustomerDetails> getCustomerByPhoneNumber(String phoneNumber) {
        return Optional.ofNullable(customers.get(phoneNumber));
    }

    // Filter call durations by a range
    public List<CallRecord> filterCallDurationByRange(CustomerDetails customer, long minDuration, long maxDuration) {
        return Optional.ofNullable(customer.getCallRecord())
                .orElse(Collections.emptyList())
                .stream()
                .filter(call -> call.getDurationInSeconds() >= minDuration && call.getDurationInSeconds() <= maxDuration)
                .collect(Collectors.toList());
    }

    // Sort longest calls
    public List<CallRecord> sortLongestCall(CustomerDetails customer) {
        return Optional.ofNullable(customer.getCallRecord())
                .orElse(Collections.emptyList())
                .stream()
                .sorted(Comparator.comparingLong(CallRecord::getDurationInSeconds).reversed())
                .collect(Collectors.toList());
    }

    // Get frequent callers
    public List<Map.Entry<String, Long>> getFrequentCallers(CustomerDetails customer) {
        return Optional.ofNullable(customer.getCallRecord())
                .orElse(Collections.emptyList())
                .stream()
                .collect(Collectors.groupingBy(CallRecord::getPhoneNumber, Collectors.counting()))
                .entrySet()
                .stream()
                .sorted(Map.Entry.<String, Long>comparingByValue().reversed())
                .collect(Collectors.toList());
    }

    // Group customers by operator
    public Map<String, List<CustomerDetails>> groupByOperator() {
        return customers.values()
                .stream()
                .collect(Collectors.groupingBy(CustomerDetails::getOperator));
    }

    // Group customers by location
    public Map<String, List<CustomerDetails>> groupByLocation() {
        return customers.values()
                .stream()
                .collect(Collectors.groupingBy(CustomerDetails::getLocation));
    }
}
