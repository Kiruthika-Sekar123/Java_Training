package com.plan_service.Entity;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "plans")
public class Plan {

    @Id
    @Column(name = "plan_id")
    private String planId;

    @Column(name = "description")
    private String description;

    @Column(name = "price")
    private double price;

    @Column(name = "validity_days")
    private int validityDays;

    @CreationTimestamp
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    // Getters and Setters

    public String getPlanId() {
        return planId;
    }

    public void setPlanId(String planId) {
        this.planId = planId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getValidityDays() {
        return validityDays;
    }

    public void setValidityDays(int validityDays) {
        this.validityDays = validityDays;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
}
