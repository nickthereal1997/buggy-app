package com.buggyapp.blockedapp;

/**
 * 线程阻塞演示 - 工作线程
 * 
 * 职责：尝试调用同步方法，因锁被占用而进入 BLOCKED 状态
 * 
 * 运行方式：
 * BlockedAppDemo 会启动 10 个 AppThread 实例
 * 第一个成功获取锁并休眠，其余 9 个进入 BLOCKED 状态
 * 
 * @author Ram Lakshmanan
 */
public class AppThread extends Thread {

	/**
	 * 线程执行逻辑
	 * 
	 * 注意：当多个线程同时启动时，只有一个能获取 AppObject.class 锁，
	 * 其他线程会在 getSomething() 方法入口处阻塞
	 */
	@Override
	public void run() {
		
		AppObject.getSomething();
	}
}
