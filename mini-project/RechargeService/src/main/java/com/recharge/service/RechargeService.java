package com.recharge.service;

import java.util.List;
import java.util.Optional;

import com.recharge.dto.RechargeRequestDto;
import com.recharge.dto.RechargeResponseDto;
import com.recharge.model.Recharge;

import java.util.List;

import java.util.List;
import java.util.Optional;

public interface RechargeService {

    RechargeResponseDto createRecharge(RechargeRequestDto requestDto);

    List<RechargeResponseDto> getRechargesByCustomerId(String customerId);

    Optional<RechargeResponseDto> getRechargeById(String rechargeId);

    void deleteRecharge(String rechargeId);
}
