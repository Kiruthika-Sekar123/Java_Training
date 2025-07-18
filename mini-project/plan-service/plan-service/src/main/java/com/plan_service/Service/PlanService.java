package com.plan_service.Service;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.plan_service.Entity.Plan;
import com.plan_service.Repository.PlanRepository;

@Service
public class PlanService {

    @Autowired
    private PlanRepository planRepository;

    public List<Plan> getAllPlans() {
        return planRepository.findAll();
    }

    public Plan getPlanById(String id) {
        return planRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Plan not found"));
    }

    public Plan createPlan(Plan plan) {
        // Generate UUID if not set
        if (plan.getPlanId() == null || plan.getPlanId().isEmpty()) {
            plan.setPlanId(UUID.randomUUID().toString());
        }
        return planRepository.save(plan);
    }

    public Plan updatePlan(String id, Plan updatedPlan) {
        Plan plan = getPlanById(id);
        plan.setDescription(updatedPlan.getDescription());
        plan.setPrice(updatedPlan.getPrice());
        plan.setValidityDays(updatedPlan.getValidityDays());
        return planRepository.save(plan);
    }

    public void deletePlan(String id) {
        planRepository.deleteById(id);
    }
}
