package com.telecomcustomersystem;

import java.util.List;
import java.util.Scanner;

import com.TelecomSystem.Model.CallRecord;
import com.TelecomSystem.Model.Customer;
import com.TelecomSystem.Service.CallService;
import com.TelecomSystem.Service.CustomerService;

public class TelecomCustomerSystem {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        CustomerService customerService = new CustomerService(); 
        Customer currentCustomer = null;
        CallService callService = null; 
        
        while (true) {
            System.out.println("\n--------Telecom Customer Management System-----");
            System.out.println("1. Register a Phone Number");
            System.out.println("2. Show All Customers");
            System.out.println("3. Make a Call");
            System.out.println("4. Show Call History");
            System.out.println("5. Subscribe to VAS");
            System.out.println("6. Unsubscribe from VAS");
            System.out.println("7. View Subscribed Services");
            System.out.println("8. File a Complaint");
            System.out.println("9. View Complaints");         
            System.out.println("10. Exit");
          
            
            System.out.print("Choose an option: ");

            int option = scanner.nextInt();
            scanner.nextLine();

            switch (option) {
                case 1:
                    System.out.println("Provide the Details to Register Phone Number");
                    System.out.print("Name: ");
                    String name = scanner.nextLine();
                    System.out.print("Phone Number: ");
                    String phoneNumber = scanner.nextLine();
                    System.out.print("Address Proof: ");
                    String addressProof = scanner.nextLine();

                    currentCustomer = new Customer(name, phoneNumber, addressProof);
                    customerService.addCustomer(currentCustomer);
                    callService = new CallService(currentCustomer);  // Initialize for this customer
                    System.out.println("Customer registered successfully.");
                    break;

                case 2:
                    customerService.showCustomerDetails();
                    break;

                case 3:
                    if (currentCustomer == null) {
                        System.out.println("No customer registered. Please register first.");
                        break;
                    }

                    System.out.print("Enter the number to call: ");
                    String numberToCall = scanner.nextLine();

                    callService.outGoingCall(numberToCall);

                    System.out.print("Do you want to cut the call? (Yes/No): ");
                    String opt = scanner.nextLine();

                    if (callService.outGoingCall(numberToCall, opt)) {
                        System.out.println("Call to " + numberToCall + " has been cut.");
                    } else {
                        System.out.println("Call still ongoing");
                    }
                    break;

                case 4:
                    if (currentCustomer == null) {
                        System.out.println("No customer registered.");
                        break;
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
                            System.out.println(call);  // Make sure CallRecord has a proper toString()
                        }
                    }
                    break;

                case 5:
                    if (currentCustomer == null) {
                        System.out.println("No customer registered.");
                        break;
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

                    String service = null;
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
                    	customerService.subscribeService(service);  // Use customer instance, no new SubscribeService created here
                        System.out.println("Subscribed to " + service);
                    } else {
                        System.out.println("Invalid VAS option.");
                    }
                    System.out.println("Subscribed Services: " + customerService.getSubscribedServices());
                    break;
                    
                case 6:
                	
                    
                case 7:
                	customerService.getSubscribedServices();
                	break;


                case 8:
                    if (currentCustomer == null) {
                        System.out.println("No customer registered.");
                        break;
                    }
                    System.out.print("Enter your complaint: ");
                    String complaint = scanner.nextLine();
                    customerService.fileComplaint(complaint);
                    System.out.println("Complaint submitted.");
                    break;
                    
                case 9:
                	customerService.getComplaints();
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
    }
}
