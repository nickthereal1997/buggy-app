package com.buggyapp.slowfinalize;

public class SlowFinalizeDemo {

	public static void start() {
		
		long counter = 0;
		
		while (true) {
		
			new Object1("my-fun-data-" + counter);
			System.out.println("created: " + counter++);
		}
	}	
}
