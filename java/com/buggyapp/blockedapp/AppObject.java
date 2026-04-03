package com.buggyapp.blockedapp;

/**
 * 线程阻塞演示 - 同步锁持有者
 * 
 * 职责：通过 synchronized 静态方法和无限休眠，模拟应用无响应场景
 * 
 * 问题原理：
 * 1. 方法使用 static synchronized，锁对象是 AppObject.class
 * 2. 第一个线程获取锁后进入无限休眠（10分钟）
 * 3. 后续线程因无法获取锁而进入 BLOCKED 状态
 * 
 * 排查方法：
 * - jstack <pid> | grep -A 1 "java.lang.Thread.State: BLOCKED"
 * - jconsole 查看线程监控页面的 BLOCKED 线程
 * 
 * @author Ram Lakshmanan
 */
public class AppObject {

	/**
	 * 同步方法，获取 AppObject.class 锁后无限休眠
	 * 
	 * 注意：第一个进入的线程会持有锁 10 分钟，
	 * 其他线程在此期间会被阻塞在方法入口处
	 */
	public static synchronized void getSomething() {

		// 第一个线程获取锁后进入无限休眠
		// 其他线程无法获取锁，进入 BLOCKED 状态
		while (true) {
			
			try {
				
				Thread.sleep(10 * 60 * 1000);  // 休眠 10 分钟
			} catch (Exception e) {}
		}
	
	}
}
