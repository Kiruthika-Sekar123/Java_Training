package com.telecomcustomersystem.Model;

import java.util.ArrayList;
import java.util.List;

public class Customer {
    private int CustomerId;
    private String name;
    private String phoneNumber;
    private String addressProof;
    private List<String> subscribedServices = new ArrayList<>();
    private List<String> complaints = new ArrayList<>();

public Customer(String name, String phoneNumber, String addressProof) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.addressProof = addressProof;
    }

    public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public void setSubscribedServices(List<String> subscribedServices) {
		this.subscribedServices = subscribedServices;
	}
	
	 public String getAddressProof() {
			return addressProof;
		}

		public void setAddressProof(String addressProof) {
			this.addressProof = addressProof;
		}

		public void setComplaints(List<String> complaints) {
			this.complaints = complaints;
		}


    public void addSubscribedService(String service) {
        if (!subscribedServices.contains(service)) {
            subscribedServices.add(service);
        }
    }
    
	public List<String> getSubscribedServices() {
        return subscribedServices;
    }

    public void addComplaint(String complaint) {
        complaints.add(complaint);
    }

    public List<String> getComplaints() {
        return complaints;
    }
    

	public int getCustomerId() {
		return CustomerId;
	}

	public void setCustomerId(int number) {
		CustomerId = number;
	}

	public boolean removeSubscribedService(String service) {
		return subscribedServices.remove(service);
	}

	 @Override
	public String toString() {
		return "Customer [name=" + name + ", phoneNumber=" + phoneNumber + ", addressProof=" + addressProof
				+ ", subscribedServices=" + subscribedServices + ", complaints=" + complaints + "]";
	}

}
