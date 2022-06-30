package com.buggyapp.blockedapp;

public class BlockedAppDemo {

	public static void start() {
		
		System.out.println("App started");
		for (int counter = 0; counter < 10; ++counter) {

			// Launch 10 threads.
			new AppThread().start();
		}
		
		System.out.println("App became unresponsive");		
	}
}
