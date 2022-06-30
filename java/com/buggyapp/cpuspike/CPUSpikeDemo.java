package com.buggyapp.cpuspike;

/**
 * 
 * @author Ram Lakshmanan
 */
public class CPUSpikeDemo {

	public static void start() {
		
		new CPUSpikerThread().start();
		new CPUSpikerThread().start();
		new CPUSpikerThread().start();
		new CPUSpikerThread().start();
		new CPUSpikerThread().start();
		new CPUSpikerThread().start();
		System.out.println("6 threads launched!");
	}
}
