package com.telecom.Implservice;

import java.util.concurrent.ConcurrentHashMap;
import com.telecom.Inteface.CustomerService;
import com.telecom.model.Customer;

import java.util.Map;

public class CustomerServiceImpl implements CustomerService {
    private final Map<String, Customer> customers = new ConcurrentHashMap<>();

    @Override
    public void addCustomer(Customer customer) {
        customers.put(customer.getId(), customer);
    }

    @Override
    public void updateCustomer(Customer customer) {
        customers.put(customer.getId(), customer);
    }

    @Override
    public void deleteCustomer(String customerId) {
        customers.remove(customerId);
    }

    @Override
    public Customer getCustomer(String customerId) {
        return customers.get(customerId);
    }
}
