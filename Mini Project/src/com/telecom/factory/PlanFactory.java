package com.telecom.factory;

import com.telecom.Implservice.PostpaidPlan;
import com.telecom.Implservice.PrepaidPlan;
import com.telecom.Inteface.Plan;

public class PlanFactory {
    public static Plan getPlan(String planType) {
        if ("prepaid".equalsIgnoreCase(planType)) {
            return new PrepaidPlan();
        } else if ("postpaid".equalsIgnoreCase(planType)) {
            return new PostpaidPlan();
        }
        throw new IllegalArgumentException("Unknown plan type: " + planType);
    }
}
