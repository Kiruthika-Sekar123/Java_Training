package com.infovision.sharemarket.trading.model;


public abstract class User {
    protected String userId;
    protected String name;

    public User(String name) {
        this.name = name;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
		this.userId = userId;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
        return name;
    }

    public void login() {
        System.out.println(name + " has logged in.");
    }

    public abstract void performRole();
}
