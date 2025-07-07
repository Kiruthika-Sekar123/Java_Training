package com.mavenproject;

import java.util.*;

public class MemoryUsageDemo {

    // Helper method to get used memory in bytes after GC
    public static long getUsedMemory() {
        System.gc(); // Request garbage collection
        try {
            Thread.sleep(100); // Wait a bit for GC to complete
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Runtime runtime = Runtime.getRuntime();
        return runtime.totalMemory() - runtime.freeMemory();
    }

    // Measure memory used by creating and filling a collection
    public static long measureMemoryUsage(Collection<?> collection) {
        long before = getUsedMemory();
        // The collection is passed filled, so just measure after filling
        long after = getUsedMemory();
        return after - before;
    }

    public static void main(String[] args) {
        int numElements = 1_000_000;

        // Measure memory usage of ArrayList
        System.out.println("Measuring ArrayList memory usage...");
        System.gc();
        long beforeArrayList = getUsedMemory();
        List<Integer> arrayList = new ArrayList<>();
        for (int i = 0; i < numElements; i++) {
            arrayList.add(i);
        }
        long afterArrayList = getUsedMemory();
        long arrayListMemory = afterArrayList - beforeArrayList;
        System.out.println("ArrayList memory usage for " + numElements + " elements: " + arrayListMemory / (1024 * 1024) + " MB");

        // Measure memory usage of LinkedList
        System.out.println("\nMeasuring LinkedList memory usage...");
        System.gc();
        long beforeLinkedList = getUsedMemory();
        List<Integer> linkedList = new LinkedList<>();
        for (int i = 0; i < numElements; i++) {
            linkedList.add(i);
        }
        long afterLinkedList = getUsedMemory();
        long linkedListMemory = afterLinkedList - beforeLinkedList;
        System.out.println("LinkedList memory usage for " + numElements + " elements: " + linkedListMemory / (1024 * 1024) + " MB");

        // Measure memory usage of HashSet
        System.out.println("\nMeasuring HashSet memory usage...");
        System.gc();
        long beforeHashSet = getUsedMemory();
        Set<Integer> hashSet = new HashSet<>();
        for (int i = 0; i < numElements; i++) {
            hashSet.add(i);
        }
        long afterHashSet = getUsedMemory();
        long hashSetMemory = afterHashSet - beforeHashSet;
        System.out.println("HashSet memory usage for " + numElements + " elements: " + hashSetMemory / (1024 * 1024) + " MB");
    }
}

