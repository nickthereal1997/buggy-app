package com.buggyapp.oomcrash;

import java.util.HashMap;
import java.util.Map;

/**
 * 
 * @author Ram Lakshmanan
 */
public class OOMCrash {

	/**
	 * 启动OOM崩溃演示
	 * 通过无限向HashMap添加元素导致OutOfMemoryError
	 */
	public static void start() {
		
		Map<String, String> map = new HashMap<>();
			
		long counter = 0;
		while (true) {
		
			counter++;
			map.put("key-" + counter, "value-" + counter);
			
			if (counter % 1000 == 0) {
				System.out.println("Added " + counter + " elements");
			}
		}
	}
	
	public static void main(String args[]) {
		start();
	}
}
