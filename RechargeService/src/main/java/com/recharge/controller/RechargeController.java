package com.recharge.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.recharge.dto.RechargeRequestDto;
import com.recharge.dto.RechargeResponseDto;
import com.recharge.service.RechargeService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/recharges")
public class RechargeController {

    private final RechargeService rechargeService;

    public RechargeController(RechargeService rechargeService) {
        this.rechargeService = rechargeService;
    }

    // Create a new recharge
    @PostMapping
    public ResponseEntity<RechargeResponseDto> createRecharge(@RequestBody RechargeRequestDto requestDto) {
        RechargeResponseDto savedRecharge = rechargeService.createRecharge(requestDto);
        return new ResponseEntity<>(savedRecharge, HttpStatus.CREATED);
    }

    // Get recharge by ID
    @GetMapping("/{rechargeId}")
    public ResponseEntity<RechargeResponseDto> getRechargeById(@PathVariable String rechargeId) {
        Optional<RechargeResponseDto> rechargeOpt = rechargeService.getRechargeById(rechargeId);
        return rechargeOpt
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Get all recharges for a customer
    @GetMapping("/customer/{customerId}")
    public ResponseEntity<List<RechargeResponseDto>> getRechargesByCustomer(@PathVariable String customerId) {
        List<RechargeResponseDto> recharges = rechargeService.getRechargesByCustomerId(customerId);
        return ResponseEntity.ok(recharges);
    }

    // Delete recharge by ID
    @DeleteMapping("/{rechargeId}")
    public ResponseEntity<Void> deleteRecharge(@PathVariable String rechargeId) {
        rechargeService.deleteRecharge(rechargeId);
        return ResponseEntity.noContent().build();
    }
}