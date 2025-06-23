package com.TelecomSystem.Service;

import java.util.ArrayList;
import java.util.List;

public class SubscribeService {
	private List<String> subscribedServices = new ArrayList<>();
	
	public void subscribeService(String service) {
        if (!subscribedServices.contains(service)) {
            subscribedServices.add(service);
            System.out.println(service + " subscribed successfully.");
        } else {
            System.out.println("Already subscribed to " + service);
        }
    }

    public List<String> getSubscribedServices() {
        return subscribedServices;
    }

}
