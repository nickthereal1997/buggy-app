package com.buggyapp.threadleak;

public class ForeverThread extends Thread {

	@Override
	public void run() {
	
		while (true) {
		
			try {
				
				Thread.sleep(10 * 60 * 1000);
			} catch (Exception e) {}
		}
	}
}
