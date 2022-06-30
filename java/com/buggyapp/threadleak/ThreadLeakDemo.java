package com.buggyapp.threadleak;

/**
 * Created infinite number of threads
 * 
 * @author Ram Lakshmanan
 */
public class ThreadLeakDemo {

	
	public static void start() {
		
		System.out.println("Thread App started");
		while (true) {
			
			try {
			
				// Failed to put thread to sleep.
				Thread.sleep(100);
			} catch (Exception e) {				
			}
			
			new ForeverThread().start();
		}
	}
}
