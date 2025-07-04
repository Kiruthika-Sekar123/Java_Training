package com.telecom.Implservice;

import com.telecom.Inteface.BillObserver;
import com.telecom.model.Customer;

public class CustomerNotificationService implements BillObserver {
    @Override
    public void update(Customer customer, double amount) {
        System.out.printf("Notification: Bill for %s (phone: %s) is $%.2f%n",
            customer.getName(), customer.getPhoneNumber(), amount);
    }
}
