package com.buggyapp;

import com.buggyapp.blockedapp.BlockedAppDemo;
import com.buggyapp.cpuspike.CPUSpikeDemo;
import com.buggyapp.deadlock.DeadLockDemo;
import com.buggyapp.io.IODemo;
import com.buggyapp.memoryleak.MemoryLeakDemo;
import com.buggyapp.memoryleaknooom.MemoryLeakNoOOMDemo;
import com.buggyapp.memoryleakthread.ThreadMemoryLeakDemo;
import com.buggyapp.sampleapp.SampleAppDemo;
import com.buggyapp.slowfinalize.SlowFinalizeDemo;
import com.buggyapp.stackoverflow.StackOverflowDemo;
import com.buggyapp.threadleak.ThreadLeakDemo;

/**
 * Buggy App that will simulate various JVM problems, used
 * for training purposes.
 * 
 * @author Ram Lakshmanan
 */
public class LaunchPad {

	public static void main(String args[]) {
		
		if (args == null || args.length < 1) {
			printUsage(args);
			return;
		}
		
		System.out.println("Application started!");

		try {
			
			switch (args[0]) {
			
			case "sample": 
				// Sample application. Producer thread creates objects and consumer
				// thread deletes those objects.
				// usage: java -Xmx256m -jar buggyApp.jar sample
				SampleAppDemo.start();
				break;
	
			case "bug1": 
			case "PROBLEM_OOM":				
				// HashMap that grows forever. Results in OutOfMemoryError
				// usage: java -Xmx512m -jar buggyApp.jar bug1
				MemoryLeakDemo.start();
				break;
			
			case "bug1.1": 
				// HashMap that grows forever. Results in OutOfMemoryError
				// usage: java -Xmx512m -jar buggyApp.jar bug1.1
				ThreadMemoryLeakDemo.start();
				break;
				
			case "bug1.2":
			case "PROBLEM_MEMORY":
				// HashMap that will grows to eat 80 - 90% of memory. But it will NOT cause OutOfMemoryError
				// usage: java -Xmx512m -jar buggyApp.jar PROBLEM_MEMORY
				MemoryLeakNoOOMDemo.start();
				break;
				
	
			case "bug2":	
				// Putting Finalizer thread to sleep
				// usage: java -Xmx512m -jar buggyApp.jar bug2
				SlowFinalizeDemo.start();
				break;
				
			case "bug3":
			case "PROBLEM_CPU":
				// Causes CPU to spike, because of infinite looping
				// usage: java -Xmx256m -jar buggyApp.jar bug3
				System.out.println("Check out CPU consumption. It might spike up!");
				CPUSpikeDemo.start();
				break;
				
			case "bug4":
			case "PROBLEM_THREADLEAK":
				// Causes thread leakage. Infinite number of threads are created
				// usage: java -Xmx1g -jar buggyApp.jar bug4
				ThreadLeakDemo.start();
				break;
	
			case "bug5":
			case "PROBLEM_BLOCKED":				
				// Puts multiple threads in to blocked state
				// usage: java -Xmx1g -jar buggyApp.jar bug5
				BlockedAppDemo.start();
				break;
	
			case "bug6":
			case "PROBLEM_DEADLOCK":				
				// Creates Deadlock
				// usage: java -Xmx1g -jar buggyApp.jar bug6
				DeadLockDemo.start();
				break;
	
			case "bug7":
			case "PROBLEM_STACKOVERFLOW":				
				// Creates StackOverflow error
				// usage 1: java -Xmx256m -Xss128k -jar buggyApp.jar bug7
				// usage 2: java -Xmx256m -Xss1m -jar buggyApp.jar bug7
				// #2 will crash very soon. #1 will run for more time.
				StackOverflowDemo demo = new StackOverflowDemo();
				try {
					demo.start();
				} catch (Throwable t) {
					System.out.println("total iterations:" + demo.counter);
					t.printStackTrace();
				}
				break;
				
			case "bug8":
			case "PROBLEM_IO":
				IODemo ioDemo = new IODemo();
				ioDemo.start();
				break;
				
			default: 
				printUsage(args);
			}
		} catch (Throwable t) {
			
			System.out.println(t);
			t.printStackTrace();
		}

	}
	
	/**
	 * Prints sample usage.
	 */
	public static void printUsage(String[] args) {
		
		StringBuilder builder = new StringBuilder("Invalid argument: ");
		for (String arg : args) {
			builder.append(arg)
					.append(" ");
		}
		
		builder.append("\n.Example Usage: java -jar buggyApp.jar bug1");
		System.out.println(builder);
	}
}
