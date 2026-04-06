package com.buggyapp.oomcrash;

import java.util.HashMap;
import java.util.Map;

/**
 * 
 * @author Ram Lakshmanan
 */
public class OOMNoCrash {

	/**
	 * 启动捕获OOM但不崩溃演示
	 * 捕获OutOfMemoryError后应用程序继续运行
	 */
	public static void start() throws Exception {
		
		try {
			
			Map<String, String> map = new HashMap<>();
			
			long counter = 0;
			while (true) {
			
				map.put("key-" + counter, "value-" + counter);
				
				counter++;				
				if (counter % 1000 == 0) {
					System.out.println("Added " + counter + " elements");
				}
			}
		} catch (Throwable e) {

			System.out.println(e.getClass() + " caught! Application will not crash.");
			doSomework();
		}		
	}
	
	public static void doSomework() {
		
		System.out.println("2 + 2 = " + (2 + 2));
	}
	
	public static void main(String args[]) throws Exception {
		start();
	}
}
