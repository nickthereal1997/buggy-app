package com.buggyapp.inefficientlist;

import java.util.ArrayList;
import java.util.List;

public class ListWithOutCapacityDemo {

	public static final String DEMO_STRING = "I am a demo string";
	private static int MAX_ELEMENTS = 100000;

	private static List<List<String>> parentList = new ArrayList<>();
	
	/**
	 * 启动无初始容量List演示
	 * 演示ArrayList不指定初始容量时的扩容开销
	 */
	public static void start() {
		
		System.out.println("Starting List Without Capacity Demo...");
		System.out.println("Creating " + MAX_ELEMENTS + " ArrayLists without initial capacity...");
		
		long startTime = System.currentTimeMillis();
		int counter = 0;		
		while (counter++ < MAX_ELEMENTS) {
		
			List<String> childList = new ArrayList<>();
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
