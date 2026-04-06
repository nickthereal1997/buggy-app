package com.buggyapp.inefficientlist;

import java.util.ArrayList;
import java.util.List;

public class ListWithCapacityDemo {

	public static final String DEMO_STRING = "I am a demo string";
	private static int MAX_ELEMENTS = 100000;

	private static List<List<String>> parentList = new ArrayList<>(MAX_ELEMENTS);
	
	/**
	 * 启动带初始容量List演示
	 * 演示ArrayList指定初始容量时的性能优势
	 */
	public static void start() {
		
		System.out.println("Starting List With Capacity Demo...");
		System.out.println("Creating " + MAX_ELEMENTS + " ArrayLists with initial capacity...");
		
		long startTime = System.currentTimeMillis();
		int counter = 0;		
		while (counter++ < MAX_ELEMENTS) {
		
			List<String> childList = new ArrayList<>(1);
			childList.add(DEMO_STRING);
			
			parentList.add(childList);
		}
		
		long duration = System.currentTimeMillis() - startTime;
		System.out.println("Completed in " + duration + " ms");
		System.out.println("Total elements: " + parentList.size());
	}
	
	public static void main(String args[]) {
		start();
	}
}
