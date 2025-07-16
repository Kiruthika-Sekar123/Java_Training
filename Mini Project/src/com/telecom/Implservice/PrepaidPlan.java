package com.telecom.Implservice;

import com.telecom.Inteface.Plan;

public class PrepaidPlan implements Plan {
    private static final double RATE_PER_SECOND = 0.02;

    public double calculateBill(long seconds) {
        return seconds * RATE_PER_SECOND;
    }
}
