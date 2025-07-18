package com.plan_service.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.plan_service.Entity.Plan;

@Repository
public interface PlanRepository extends JpaRepository<Plan, String> {
    // You can add custom query methods here if needed
}

