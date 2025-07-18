package com.customer.service;

import com.customer.entity.Customer;
import com.customer.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    // Get all customers
    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    // Get customer by ID
    public Customer getCustomerById(String id) {
        return customerRepository.findById(id)
                .orElse(null); // You can throw a custom exception if preferred
    }

    // Create new customer
    public Customer createCustomer(Customer customer) {
        if (customer.getCustomerId() == null) {
            customer.setCustomerId(UUID.randomUUID().toString());  // generate id before saving
        }
        return customerRepository.save(customer);
    }


    // Update customer
    public Customer updateCustomer(String id, Customer updatedCustomer) {
        Optional<Customer> optional = customerRepository.findById(id);
        if (optional.isPresent()) {
            Customer customer = optional.get();
            customer.setName(updatedCustomer.getName());
            customer.setEmail(updatedCustomer.getEmail());
            customer.setMobileNumber(updatedCustomer.getMobileNumber());
            return customerRepository.save(customer);
        }
        return null;
    }

    // Delete customer
    public boolean deleteCustomer(String id) {
        if (customerRepository.existsById(id)) {
            customerRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
