package com.buggyapp.deadlock;

/**
 * 死锁演示 - 线程 A
 * 
 * 职责：调用 CoolObject.method1()，触发死锁的第一部分
 * 
 * 执行流程：
 * 1. 获取 CoolObject.class 锁（进入 synchronized method1）
 * 2. 休眠 10 秒
 * 3. 调用 HotObject.method2()，尝试获取 HotObject.class 锁
 * 4. 如果 ThreadB 已持有 HotObject 锁，则进入 BLOCKED 状态
 * 
 * @author Ram Lakshmanan
 */
public class ThreadA extends Thread {

	/**
	 * 线程执行逻辑
	 * 
	 * 调用 CoolObject.method1()，开始死锁链条
	 */
	@Override	
	public void run() {
		CoolObject.method1();
	}
}
