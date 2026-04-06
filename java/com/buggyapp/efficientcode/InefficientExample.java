package com.buggyapp.efficientcode;

import java.util.ArrayList;
import java.util.List;

public class InefficientExample {

	private List<User> users = new ArrayList<>();
	
	/**
	 * 添加用户（立即初始化版本）
	 * 演示立即初始化ArrayList的内存开销
	 */
	public void addUser(User user) {
		
		users.add(user);
	}
	
	/**
	 * 启动低效代码演示
	 * 演示立即初始化的内存开销
	 */
	public static void start() {
		
		System.out.println("Starting Inefficient Code Demo...");
		System.out.println("Demonstrating eager initialization overhead...");
		
		long startTime = System.currentTimeMillis();
		int count = 100000;
		
		for (int i = 0; i < count; i++) {
			InefficientExample example = new InefficientExample();
			// 注意：这里不调用addUser，但users列表已经被创建
		}
		
		long duration = System.currentTimeMillis() - startTime;
		System.out.println("Created " + count + " instances with eager init in " + duration + " ms");
		System.out.println("Memory wasted by initializing unused ArrayLists");
	}
	
	public static void main(String args[]) {
		start();
	}
}
