package com.telecom.Inteface;

import com.telecom.model.Customer;

public interface BillObserver {
    void update(Customer customer, double amount);
}

