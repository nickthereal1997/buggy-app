package com.buggyapp.sampleapp;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 
 * @author Ram Lakshmanan
 */
public class SampleAppDemo {

	public static int THRESHOLD = 1000;
	
	public static Map<Long, String> s_map = new ConcurrentHashMap<>();
	
	public static void start() {
		
		try {
						
			for (int counter = 1; counter <= 5; ++counter) {
				
				Producer producer = new Producer();
				producer.setName("Producer-" + counter);
				producer.start();
			}
			
			Consumer consumer = new Consumer();
			consumer.setName("Consumer");
			consumer.start();
		} catch (Throwable t) {
			
			System.out.println("SampleAppDemo Failed!!");
			t.printStackTrace();
		}		
	}
}
