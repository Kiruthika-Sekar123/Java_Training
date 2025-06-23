package com.telecomcustomersystem.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.telecomcustomersystem.Model.Customer;

public class SubscribeService {

 
    private Map<String, Customer> customerMap;

    public SubscribeService() {
        this.customerMap = new HashMap<>();
    }

    public void addCustomer(Customer customer) {
        customerMap.put(customer.getPhoneNumber(), customer);
    
    }
   
    public void subscribeService(String phoneNumber, String service) {
        Customer customer = customerMap.get(phoneNumber);

        if (customer != null) {
            
            customer.addSubscribedService(service);
            System.out.println("Customer " + customer.getName() + " successfully subscribed to " + service);
        } else {
           
            System.out.println("Customer with phone number " + phoneNumber + " not found.");
        }
    }

   
    public void unsubscribeService(String phoneNumber, String service) {
        Customer customer = customerMap.get(phoneNumber);

        if (customer != null) {
           
            boolean removed = customer.removeSubscribedService(service);

            if (removed) {
                System.out.println("Customer " + customer.getName() + " has unsubscribed from " + service);
            } else {
                System.out.println("Service " + service + " not found for customer " + customer.getName());
            }
        } else {
            System.out.println("Customer with phone number " + phoneNumber + " not found.");
        }
    }

    
    public List<String> viewSubscribedServices(String phoneNumber) {
        System.out.println("Looking up customer with phone number: " + phoneNumber);
        Customer customer = customerMap.get(phoneNumber);

        if (customer != null) {
            System.out.println("Customer found: " + customer.getName());
            System.out.println("Subscribed services: " + customer.getSubscribedServices());
            return new ArrayList<>(customer.getSubscribedServices());
        } else {
            System.out.println("Customer with phone number " + phoneNumber + " not found.");
            return new ArrayList<>();  // Return empty list if no customer found
        }
    }

    }

