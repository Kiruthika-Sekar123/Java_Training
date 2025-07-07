package com.mavenproject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public class BankAccount{
	public static void main(String[] args) {
//		   HashSet<Integer> set1 = new HashSet<>(Arrays.asList(11, 78, 90, 76, 56));
//	        HashSet<Integer> set2 = new HashSet<>(Arrays.asList(14, 78, 45, 98, 90));
////	        List<Integer> list = new ArrayList<>(); // fixed typo from "lsit" to "list"
//
////		for(Integer s : set1) {
////			if(!set2.contains(s)) {
////				list.add(s);
////			}
////		}
//	        
////	    set1.removeAll(set2); 
////	    List<Integer> list = new ArrayList<>(); 
////	    list.addAll(set1);
////		System.out.println(list);
//		
//		int sum = Collections.max(list) + Collections.min(list);
		
//		System.out.println(sum);
		
//		 List<Integer> list = new ArrayList<>(Arrays.asList(2,3,2,4));
//		 int k=3;
//		 list.remove(Integer.valueOf(k));
//		// System.out.println(list);
//		 
//		 List<Integer> filteredList = list.stream()
//				    .filter(value -> value != k)
//				    .collect(Collectors.toList());
//
////				System.out.println(filteredList);
//				
//				Optional<Integer> max = list.stream().max(Comparator.naturalOrder());
////				max.ifPresent(System.out::println);
//				
//				 List<String> list1 = new ArrayList<>(Arrays.asList("hello","world","hello"));	
//
////                  list1.stream().map(value -> value.toUpperCase()).forEach(System.out::println);
//				long s = list1.stream().filter(value -> value.startsWith("h")).count();
//	                 System.out.println(s);	
//	                 
//	                 System.out.println(10 + 20 + "30" + 40);
//	                 
//	                 String s1 = "abc";
//	                 String s2 = "abc";
//	                 System.out.println(s1.equals(s2));
	                 
	                 
	                 List<String> list = Arrays.asList("Animesh","Animesh","srinivas","Anitha","Anitha","Pranali","pranali","Pranali");
	                 Map<String, Long> result = list.stream()
	                		 .map(String::toLowerCase)
	                         .collect(Collectors.groupingBy(
	                             name -> name,
	                             Collectors.counting()
	                         ));
	                         System.out.println(result);
	}
}