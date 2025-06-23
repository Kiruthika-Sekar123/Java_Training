package com.TelecomSystem;

import com.TelecomSystem.Exception.*;
import com.TelecomSystem.Model.CallRecord;
import com.TelecomSystem.Model.Customer;
import com.TelecomSystem.Service.CallService;
import com.TelecomSystem.Service.CustomerService;
import com.TelecomSystem.Service.FileComplaintService;
import com.TelecomSystem.Service.SubscribeService;

import java.util.List;
import java.util.Scanner;

public class TelecomCustomerSystem {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        SubscribeService subscribeService = new SubscribeService();
        FileComplaintService file = new FileComplaintService();
        CustomerService customerService = new CustomerService(subscribeService, file);
        
        Customer currentCustomer = null;
        CallService callService = null;
        String service = null;
        String numberToCall = null;
        
        try {
            while (true) {
                System.out.println("\n--------Telecom Customer Management System-----");
                System.out.println("1. Register a Phone Number");
                System.out.println("2. Show All Customers");
                System.out.println("3. Make a Call");
                System.out.println("4. Show Call History");
                System.out.println("5. Subscribe to VAS");
                System.out.println("6. View Subscribed Services");
                System.out.println("7. Unsubscribe from VAS");
                System.out.println("8. File a Complaint");
                System.out.println("9. View Complaints");
                System.out.println("10. Exit");
                System.out.print("Choose an option: ");
                
                int option = scanner.nextInt();
                scanner.nextLine();
                
                switch (option) {
                    case 1:
                        System.out.println("Provide the Details to Register Phone Number");
                        
                        // Registration logic...
                        
                        break;

                    case 2:
                        customerService.showAllCustomerDetails();
                        break;

                    case 3:
                        if (currentCustomer == null) {
                            throw new UserNotFoundException("No customer registered. Please register first.");
                        }
                       
                        do {
                            System.out.print("Enter the number to call: ");
                            numberToCall = scanner.nextLine().trim();
                            if (!numberToCall.matches("\\d{10}")) {
                                System.out.println("Enter a valid 10-digit phone number.");
                                numberToCall = "";
                            }
                        } while (numberToCall.isEmpty());
                        
                        callService.outGoingCall(numberToCall);
                        System.out.print("Do you want to cut the call? (Yes): ");
                        String opt = scanner.nextLine();

                        if (callService.outGoingCall(numberToCall, opt)) {
                            System.out.println("Call to " + numberToCall + " has been cut.");
                        }
                        break;

                    case 4:
                        if (currentCustomer == null) {
                            throw new UserNotFoundException("No customer registered.");
                        }
                        if (callService == null) {
                            System.out.println("No call history available.");
                            break;
                        }

                        List<CallRecord> history = callService.getCallHistory();
                        if (history.isEmpty()) {
                            System.out.println("No call history.");
                        } else {
                            System.out.println("Call history:");
                            for (CallRecord call : history) {
                                System.out.println(call);
                            }
                        }
                        break;

                    case 5:
                        if (currentCustomer == null) {
                            throw new UserNotFoundException("No customer registered.");
                        }
                        System.out.println("Available VAS Services:");
                        System.out.println("1. Caller Tune");
                        System.out.println("2. Missed Call Alert");
                        System.out.println("3. Call Forwarding");
                        System.out.println("4. Data Booster");
                        System.out.println("5. International Roaming");
                        System.out.print("Enter service number to subscribe: ");
                        int vasOption = scanner.nextInt();
                        scanner.nextLine();

                        switch (vasOption) {
                            case 1:
                                service = "Caller Tune";
                                break;
                            case 2:
                                service = "Missed Call Alert";
                                break;
                            case 3:
                                service = "Call Forwarding";
                                break;
                            case 4:
                                service = "Data Booster";
                                break;
                            case 5:
                                service = "International Roaming";
                                break;
                            default:
                                service = null;
                        }

                        if (service != null) {
                            customerService.subscribeService(currentCustomer.getPhoneNumber(), service);
                            System.out.println("Subscribed to " + service);
                        } else {
                            System.out.println("Invalid VAS option.");
                        }
                        break;

                    case 6:
                        if (currentCustomer == null) {
                            throw new UserNotFoundException("No customer registered.");
                        }
                        customerService.getSubscribedServices(currentCustomer.getPhoneNumber());
                        break;
                    
                    case 7:
                        if (currentCustomer == null) {
                            throw new UserNotFoundException("No customer registered.");
                        }
                        service = scanner.nextLine();
                        customerService.unsubscribeService(currentCustomer.getPhoneNumber(), service);
                        break;

                    case 8:
                        if (currentCustomer == null) {
                            throw new UserNotFoundException("No customer registered.");
                        }
                        System.out.print("Enter your complaint: ");
                        String complaint = scanner.nextLine();
                        customerService.fileComplaint(currentCustomer.getPhoneNumber(), complaint);
                        System.out.println("Complaint submitted.");
                        break;

                    case 9:
                        if (currentCustomer == null) {
                            throw new UserNotFoundException("No customer registered.");
                        }
                        customerService.getComplaints(currentCustomer.getPhoneNumber());
                        break;

                    case 10:
                        System.out.println("Exiting system...");
                        scanner.close();
                        System.exit(0);
                        break;

                    default:
                        System.out.println("Invalid option. Please try again.");
                }
            }
        } catch (UserNotFoundException ex) {
            System.out.println("Error: " + ex.getMessage());
        } catch (Exception ex) {
            System.out.println("An unexpected error occurred: " + ex.getMessage());
        }
    }
}
