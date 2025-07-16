package com.telecom.Inteface;

import com.telecom.model.Customer;

public interface CustomerService {
    void addCustomer(Customer customer);
    void updateCustomer(Customer customer);
    void deleteCustomer(String customerId);
    Customer getCustomer(String customerId);
}
