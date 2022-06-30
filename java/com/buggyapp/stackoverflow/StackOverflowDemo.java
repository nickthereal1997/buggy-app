package com.buggyapp.stackoverflow;

import java.util.ArrayList;
import java.util.List;

public class StackOverflowDemo {

	public int counter = 0;
	
	public void start() {
		
		++counter;

		//SimpleObject so0 = new SimpleObject("Simple Object created");

		if (counter % 1000 == 0) {
			
			System.out.println("Looped " + counter + " times");
		}
		
		start();		
	}
	
}
