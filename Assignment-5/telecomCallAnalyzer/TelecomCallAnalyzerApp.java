package com.telecomCallAnalyzer;

import com.telecomCallAnalyzer.Service.CallDetailsService;
import com.telecomCallAnalyzer.model.CallRecord;
import com.telecomCallAnalyzer.model.CustomerDetails;

import java.util.*;

public class TelecomCallAnalyzerApp {
    public static void main(String[] args) {
        Scanner user = new Scanner(System.in);
        CallDetailsService customerService = new CallDetailsService();
        String phoneNumber;

        System.out.println("Welcome to Telecom Customer Call Records Analyzer App");

        try {
            while (true) {
                System.out.println("\nMenu:");
                System.out.println("1. Register Phone Number");
                System.out.println("2. Make a Call");
                System.out.println("3. Show Call Durations for a Customer");
                System.out.println("4. Filter Highest Call Duration");
                System.out.println("5. Sort Longest Calls");
                System.out.println("6. Sort Frequent Calls");
                System.out.println("7. Group by Operator/Location");
                System.out.println("8. Exit");
                System.out.print("Choose an option: ");

                int option;
                try {
                    option = Integer.parseInt(user.nextLine());
                } catch (NumberFormatException e) {
                    System.out.println("Invalid input. Please enter a number for the menu option.");
                    continue;
                }

                switch (option) {
                    case 1: // Register
                        String name;
                        do {
                            System.out.print("Enter Name: ");
                            name = user.nextLine().trim();
                            if (name.isEmpty() || !name.matches("[a-zA-Z ]+")) {
                                System.out.println("Name must not be empty and should contain only letters.");
                                name = null;
                            }
                        } while (name == null);

                        do {
                            System.out.print("Enter Phone Number: ");
                            phoneNumber = user.nextLine().trim();
                            if (!phoneNumber.matches("\\d{10}")) {
                                System.out.println("Phone number must be exactly 10 digits.");
                                phoneNumber = null;
                            }
                        } while (phoneNumber == null);

                        String operator;
                        do {
                            System.out.print("Enter Operator: ");
                            operator = user.nextLine().trim();
                            if (operator.isEmpty() || !operator.matches("[a-zA-Z]+")) {
                                System.out.println("Operator must contain only letters.");
                                operator = null;
                            }
                        } while (operator == null);

                        String location;
                        do {
                            System.out.print("Enter Location: ");
                            location = user.nextLine().trim();
                            if (location.isEmpty() || !location.matches("[a-zA-Z ]+")) {
                                System.out.println("Location must contain only letters.");
                                location = null;
                            }
                        } while (location == null);

                        customerService.addCustomer(new CustomerDetails(name, phoneNumber, operator, location));
                        System.out.println("User registered successfully!");
                        break;

                    case 2: // Make a Call
                        System.out.print("Enter Caller Phone Number: ");
                        String callerPhone = user.nextLine().trim();

                        System.out.print("Enter Receiver Phone Number: ");
                        String receiverPhone = user.nextLine().trim();

                        CustomerDetails caller = customerService.getCustomerByPhoneNumber(callerPhone).orElse(null);
                        CustomerDetails receiver = customerService.getCustomerByPhoneNumber(receiverPhone).orElse(null);

                        if (caller == null || receiver == null) {
                            System.out.println("Caller or receiver not found.");
                        } else {
                            customerService.makeCall(caller, receiver);
                            System.out.println("Call Completed successfully.");
                        }
                        break;

                    case 3: // Show Call Durations
                        System.out.print("Enter customer phone number to view call history: ");
                        phoneNumber = user.nextLine();
                        Optional<CustomerDetails> customerOpt = customerService.getCustomerByPhoneNumber(phoneNumber);

                        if (customerOpt.isPresent()) {
                            List<CallRecord> history = customerOpt.get().getCallRecord();
                            if (history.isEmpty()) {
                                System.out.println("No call history found for " + phoneNumber);
                            } else {
                                System.out.println("Call history for " + phoneNumber + ":");
                                for (CallRecord record : history) {
                                    System.out.println("To: " + record.getPhoneNumber() +
                                            ", Duration: " + record.getDurationInSeconds() + " seconds");
                                }
                            }
                        } else {
                            System.out.println("Customer not found.");
                        }
                        break;

                    case 4: // Filter Highest Call Duration
                        System.out.print("Enter customer phone number to filter high call durations: ");
                        phoneNumber = user.nextLine();
                        customerOpt = customerService.getCustomerByPhoneNumber(phoneNumber);

                        if (customerOpt.isPresent()) {
                            List<CallRecord> filtered = customerService.filterCallDurationByRange(customerOpt.get(), 30, Long.MAX_VALUE);
                            if (filtered.isEmpty()) {
                                System.out.println("No calls found with duration greater than 30 seconds.");
                            } else {
                                filtered.forEach(System.out::println);
                            }
                        } else {
                            System.out.println("Customer not found.");
                        }
                        break;

                    case 5: // Sort Longest Calls
                        System.out.print("Enter customer phone number to sort by longest calls: ");
                        phoneNumber = user.nextLine();
                        customerOpt = customerService.getCustomerByPhoneNumber(phoneNumber);

                        if (customerOpt.isPresent()) {
                            List<CallRecord> sorted = customerService.sortLongestCall(customerOpt.get());
                            if (sorted.isEmpty()) {
                                System.out.println("No call records found.");
                            } else {
                                sorted.forEach(System.out::println);
                            }
                        } else {
                            System.out.println("Customer not found.");
                        }
                        break;

                    case 6: // Sort Frequent Calls
                        System.out.print("Enter customer phone number to find frequent contacts: ");
                        phoneNumber = user.nextLine();
                        customerOpt = customerService.getCustomerByPhoneNumber(phoneNumber);

                        if (customerOpt.isPresent()) {
                            List<Map.Entry<String, Long>> frequent = customerService.getFrequentCallers(customerOpt.get());
                            if (frequent.isEmpty()) {
                                System.out.println("No frequent contacts found.");
                            } else {
                                for (Map.Entry<String, Long> entry : frequent) {
                                    System.out.println("Receiver: " + entry.getKey() + " - Called " + entry.getValue() + " times");
                                }
                            }
                        } else {
                            System.out.println("Customer not found.");
                        }
                        break;

                    case 7: // Grouping
                        System.out.println("Grouping records by:");
                        System.out.println("1. Operator");
                        System.out.println("2. Location");
                        System.out.print("Choose option: ");
                        String groupChoice = user.nextLine().trim();

                        if (groupChoice.equals("1")) {
                            Map<String, List<CustomerDetails>> operatorMap = customerService.groupByOperator();
                            operatorMap.forEach((op, list) ->
                                    System.out.println("Operator: " + op + " - Customers: " + list.size()));
                        } else if (groupChoice.equals("2")) {
                            Map<String, List<CustomerDetails>> locationMap = customerService.groupByLocation();
                            locationMap.forEach((loc, list) ->
                                    System.out.println("Location: " + loc + " - Customers: " + list.size()));
                        } else {
                            System.out.println("Invalid choice.");
                        }
                        break;

                    case 8:
                        System.out.println("Exiting application. Thank You!");
                        return;

                    default:
                        System.out.println("Invalid option. Please select between 1 and 8.");
                }
            }
        } catch (Exception ex) {
            System.out.println("An unexpected error occurred: " + ex.getMessage());
            ex.printStackTrace(); // Useful for debugging
        } finally {
            user.close();
        }
    }
}
