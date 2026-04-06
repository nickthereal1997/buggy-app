package com.buggyapp.inefficientlist;

public class ArraysDemo {

	public static final String DEMO_STRING = "I am a demo string";
	private static int MAX_ELEMENTS = 100000;
	
	private static String[][] parentArray = new String[MAX_ELEMENTS][1];
	
	/**
	 * 启动数组演示
	 * 对比ArrayList，展示原生数组的性能
	 */
	public static void start() {
		
		System.out.println("Starting Arrays Demo...");
		System.out.println("Creating " + MAX_ELEMENTS + " arrays...");
		
		long startTime = System.currentTimeMillis();
		int counter = 0;
		while (counter < MAX_ELEMENTS) {
		
			String[] childArray = new String[1];			
			childArray[0] = DEMO_STRING;
			
			parentArray[counter] = childArray;
			counter++;
		}
		
		long duration = System.currentTimeMillis() - startTime;
		System.out.println("Completed in " + duration + " ms");
		System.out.println("Total elements: " + counter);
	}
	
	public static void main(String args[]) {
		start();
	}
}
