package com.buggyapp.efficientcode;

import java.util.ArrayList;
import java.util.List;

public class RandomExample {

	/**
	 * 添加用户（包含一些低效代码模式）
	 * 演示常见的代码效率问题
	 */
	public static void addUser(User user) {
		
		new User();
		
		List<User> users = new ArrayList<>();		
		users.clear();
		
		int value = 0;
		for (int counter = 1; counter <= 11; ++counter) {
			
			++value;
			users.add(user);
		}
		
		System.out.println(value);
	}
	
	/**
	 * 启动随机低效代码演示
	 * 演示一些常见的代码效率问题
	 */
	public static void start() {
		
		System.out.println("Starting Random Inefficient Code Demo...");
		System.out.println("Demonstrating common coding inefficiencies...");
		
		long startTime = System.currentTimeMillis();
		int iterations = 100000;
		
		for (int i = 0; i < iterations; i++) {
			addUser(null);
		}
		
		long duration = System.currentTimeMillis() - startTime;
		System.out.println("Completed " + iterations + " iterations in " + duration + " ms");
		System.out.println("Issues: unnecessary object creation, redundant clear() calls");
	}
	
	public static void main(String args[]) {
		start();
	}
}
