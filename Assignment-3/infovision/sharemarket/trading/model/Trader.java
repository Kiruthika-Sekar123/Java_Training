package com.infovision.sharemarket.trading.model;

import java.util.*;

public class Trader extends User {
	private List<Stock> portfolio = new ArrayList<>();
    private double balance; // Represents the trader's available funds

    public Trader(String name, double balance) {
        super(name);
        this.userId = UUID.randomUUID().toString(); 
        this.balance=balance;
    }

    public List<Stock> getPortfolio() {
        return portfolio;
    }


    public double getBalance() {
        return balance;
    }
    
    public void setBalance(double balance) {
    	this.balance = balance;
    }


    @Override
    public void performRole() {
        System.out.println(name + " is executing trading operations.");
    }

    @Override
    public String toString() {
        return String.format("Trader{id='%s', name='%s', balance=₹%.2f, portfolio=%s}",
                userId, name, balance, portfolio);
    }

}
