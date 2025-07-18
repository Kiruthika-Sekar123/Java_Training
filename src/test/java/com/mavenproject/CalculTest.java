package com.mavenproject;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
public class CalculTest {
	
      Calculator cal = new Calculator();
      
      @Test
      public void add() {
    	  assertEquals(11,cal.add(10, 1));
      }
      
      
      
}