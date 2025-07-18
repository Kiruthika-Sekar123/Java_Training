package com.recharge.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.recharge.dto.PlanDto;

@FeignClient(name = "plan-service",  url = "http://localhost:9002")
public interface PlanClient {

    @GetMapping("/plans/{planId}")
	PlanDto getPlanById(@PathVariable("planId") String planId);
}
