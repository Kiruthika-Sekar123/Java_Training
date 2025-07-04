package com.telecom.Implservice;

import com.telecom.Inteface.Plan;

public class PostpaidPlan implements Plan {
    private static final double RATE_PER_SECOND = 0.05;

    public double calculateBill(long seconds) {
        return seconds * RATE_PER_SECOND;
    }
}
