package com.buggyapp.deadlock;

/**
 * 死锁演示 - 线程 B
 * 
 * 职责：调用 HotObject.method2()，触发死锁的第二部分
 * 
 * 执行流程：
 * 1. 获取 HotObject.class 锁（进入 synchronized method2）
 * 2. 休眠 10 秒
 * 3. 调用 CoolObject.method1()，尝试获取 CoolObject.class 锁
 * 4. 如果 ThreadA 已持有 CoolObject 锁，则进入 BLOCKED 状态
 * 
 * @author Ram Lakshmanan
 */
public class ThreadB extends Thread {

	/**
	 * 线程执行逻辑
	 * 
	 * 调用 HotObject.method2()，完成死锁闭环
	 */
	@Override	
	public void run() {
		HotObject.method2();
	}	
}
