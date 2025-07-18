package com.recharge.dto;


import java.math.BigDecimal;
import java.time.LocalDate;

public class RechargeRequestDto {

    private String customerId;
    private String planId;
    private BigDecimal amount;          // Optional: can be validated/calculated on backend
    private String paymentMethod;
    private LocalDate rechargeDate;     // Optional: if not provided, default to current date

    // Constructors
    public RechargeRequestDto() {}

    public RechargeRequestDto(String customerId, String planId, BigDecimal amount, String paymentMethod, LocalDate rechargeDate) {
        this.customerId = customerId;
        this.planId = planId;
        this.amount = amount;
        this.paymentMethod = paymentMethod;
        this.rechargeDate = rechargeDate;
    }

    // Getters and Setters

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getPlanId() {
        return planId;
    }

    public void setPlanId(String planId) {
        this.planId = planId;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public LocalDate getRechargeDate() {
        return rechargeDate;
    }

    public void setRechargeDate(LocalDate rechargeDate) {
        this.rechargeDate = rechargeDate;
    }
}
