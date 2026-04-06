package com.buggyapp.efficientcode;

import java.util.ArrayList;
import java.util.List;

public class EfficientExample {

	private List<User> users;
	
	/**
	 * 添加用户（延迟初始化版本）
	 * 演示延迟初始化ArrayList以节省内存
	 */
	public void addUser(User user) {
		
		if (users == null) {
			// Lazily initializing ArrayList<> to save memory
			users = new ArrayList<>();
		}
		
		users.add(user);
	}
	
	/**
	 * 启动高效代码演示
	 * 演示延迟初始化的性能优势
	 */
	public static void start() {
		
		System.out.println("Starting Efficient Code Demo...");
		System.out.println("Demonstrating lazy initialization...");
		
		long startTime = System.currentTimeMillis();
		int count = 100000;
		
		for (int i = 0; i < count; i++) {
			EfficientExample example = new EfficientExample();
			// 注意：这里不调用addUser，所以users列表不会被创建
		}
		
		long duration = System.currentTimeMillis() - startTime;
		System.out.println("Created " + count + " instances with lazy init in " + duration + " ms");
		System.out.println("Memory saved by not initializing unused ArrayLists");
	}
	
	public static void main(String args[]) {
		start();
	}
}
