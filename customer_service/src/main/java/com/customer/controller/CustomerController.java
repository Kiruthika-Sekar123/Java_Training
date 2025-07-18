package com.customer.controller;


import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.customer.entity.Customer;
import com.customer.service.CustomerService;



@RestController
@RequestMapping("/customers")
public class CustomerController {

    private static final Logger logger = LoggerFactory.getLogger(CustomerController.class);

    @Autowired
    private CustomerService customerService;

    @GetMapping
    public List<Customer> getAllCustomers() {
        logger.info("Received GET request for all customers");
        List<Customer> customers = customerService.getAllCustomers();
        logger.info("Returning {} customers", customers.size());
        return customers;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Customer> getCustomerById(@PathVariable String id) {
        logger.info("Received GET request for customer with id: {}", id);
        Customer customer = customerService.getCustomerById(id);
        if (customer == null) {
            logger.warn("Customer not found with id: {}", id);
            return ResponseEntity.notFound().build();
        }
        logger.info("Customer found: {}", customer);
        return ResponseEntity.ok(customer);
    }

    @PostMapping
    public ResponseEntity<Customer> createCustomer(@RequestBody Customer customer) {
        logger.info("Received POST request to create customer: {}", customer);
        Customer created = customerService.createCustomer(customer);
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Customer> updateCustomer(@PathVariable String id, @RequestBody Customer customer) {
        logger.info("Received PUT request to update customer with id: {}", id);
        Customer updated = customerService.updateCustomer(id, customer);
        if (updated == null) {
            logger.warn("Customer not found for update with id: {}", id);
            return ResponseEntity.notFound().build();
        }
        logger.info("Customer updated: {}", updated);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCustomer(@PathVariable String id) {
        logger.info("Received DELETE request for customer with id: {}", id);
        boolean deleted = customerService.deleteCustomer(id);
        if (deleted) {
            logger.info("Customer deleted with id: {}", id);
            return ResponseEntity.noContent().build();
        } else {
            logger.warn("Customer not found for deletion with id: {}", id);
            return ResponseEntity.notFound().build();
        }
    }
}

