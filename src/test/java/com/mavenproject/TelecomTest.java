package com.mavenproject;

import static org.junit.Assert.assertEquals;


import org.junit.Test;
public class TelecomTest {
    
	Telecom tele = new Telecom();
	
	
	    @Test
	    public void testAddCustomer() {
	        CustomerDetails customer = new CustomerDetails("Kiruthika", "9344041348");
	        boolean result = tele.addCustomer(customer);
	        assertEquals(true, result);
	    }
	
	    @Test
	    public void testDisplay() {	
        tele.addCustomer(new CustomerDetails("Kiruthika", "9344041348"));

	        assertEquals(1, tele.user.size());
	        tele.display();
	    }
}
