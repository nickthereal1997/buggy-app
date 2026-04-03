package com.buggyapp.blockedapp;

/**
 * 线程阻塞演示入口类
 * 
 * 演示场景：多个线程竞争同一把锁，导致大部分线程进入 BLOCKED 状态，应用无响应
 * 
 * 运行方式：
 * java -jar buggyApp.jar bug5
 * 或
 * java -jar buggyApp.jar PROBLEM_BLOCKED
 * 
 * 预期结果：
 * 1. 启动 10 个线程
 * 2. 第一个线程获取锁并进入长时间休眠
 * 3. 其余 9 个线程进入 BLOCKED 状态
 * 4. 控制台输出 "App became unresponsive"
 * 
 * 排查方法：
 * - jstack <pid>：查看线程状态，发现多个 BLOCKED 线程
 * - jstack <pid> | grep "BLOCKED"：统计 BLOCKED 线程数量
 * 
 * @author Ram Lakshmanan
 */
public class BlockedAppDemo {

	/**
	 * 启动线程阻塞演示
	 * 
	 * 创建 10 个 AppThread 并启动，模拟并发场景下的锁竞争问题
	 */
	public static void start() {
		
		System.out.println("App started");
		for (int counter = 0; counter < 10; ++counter) {

			// 启动 10 个线程，它们会竞争 AppObject.class 锁
			new AppThread().start();
		}
		
		System.out.println("App became unresponsive");		
	}
}
