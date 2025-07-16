package com.telecom.singleton;


import com.telecom.Inteface.BillObserver;
import com.telecom.model.Customer;

import java.util.ArrayList;
import java.util.List;

public class BillingEngine {
    private static BillingEngine instance;
    private final List<BillObserver> observers = new ArrayList<>();

    private BillingEngine() {}

    public static synchronized BillingEngine getInstance() {
        if (instance == null) {
            instance = new BillingEngine();
        }
        return instance;
    }

    public void addObserver(BillObserver observer) {
        observers.add(observer);
    }

    public void generateAndNotifyBill(Customer customer) {
        long duration = 60; // Simulated usage
        double amount = customer.getPlan().calculateBill(duration);

        for (BillObserver observer : observers) {
            observer.update(customer, amount);
        }
    }
}
