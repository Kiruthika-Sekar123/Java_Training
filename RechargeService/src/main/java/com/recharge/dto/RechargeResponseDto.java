package com.recharge.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

public class RechargeResponseDto {

    private String status;         // e.g. "SUCCESS"
    private String message;        // e.g. "Recharge successful"

    private String rechargeId;
    private LocalDate rechargeDate;
    private LocalDate validTill;
    private BigDecimal amount;
    private String paymentMethod;

    private CustomerDto customer;  // Nested customer info
    private PlanDto plan;          // Nested plan info

    // Getters and setters

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getRechargeId() {
        return rechargeId;
    }

    public void setRechargeId(String rechargeId) {
        this.rechargeId = rechargeId;
    }

    public LocalDate getRechargeDate() {
        return rechargeDate;
    }

    public void setRechargeDate(LocalDate rechargeDate) {
        this.rechargeDate = rechargeDate;
    }

    public LocalDate getValidTill() {
        return validTill;
    }

    public void setValidTill(LocalDate validTill) {
        this.validTill = validTill;
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

    public CustomerDto getCustomer() {
        return customer;
    }

    public void setCustomer(CustomerDto customer) {
        this.customer = customer;
    }

    public PlanDto getPlan() {
        return plan;
    }

    public void setPlan(PlanDto plan) {
        this.plan = plan;
    }
}
