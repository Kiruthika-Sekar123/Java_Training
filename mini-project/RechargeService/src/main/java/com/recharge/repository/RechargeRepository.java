package com.recharge.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.recharge.model.Recharge;

import java.util.List;

public interface RechargeRepository extends JpaRepository<Recharge, String> {
    // Find all recharges for a given customer ordered by rechargeDate descending
    List<Recharge> findByCustomerIdOrderByRechargeDateDesc(String customerId);
}
