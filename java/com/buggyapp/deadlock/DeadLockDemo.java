package com.buggyapp.deadlock;

/**
 * 死锁演示入口类
 * 
 * 演示场景：经典的双线程死锁，ThreadA 和 ThreadB 互相等待对方释放锁
 * 
 * 运行方式：
 * java -jar buggyApp.jar bug6
 * 或
 * java -jar buggyApp.jar PROBLEM_DEADLOCK
 * 
 * 预期结果：
 * 1. 控制台输出 "App started"
 * 2. ThreadA 获取 CoolObject 锁，等待 HotObject 锁
 * 3. ThreadB 获取 HotObject 锁，等待 CoolObject 锁
 * 4. 两个线程永久阻塞，形成死锁
 * 
 * 死锁条件（Coffman 条件）：
 * 1. 互斥：锁是互斥的
 * 2. 占有并等待：ThreadA 占有 CoolObject 锁，等待 HotObject 锁
 * 3. 不可抢占：锁不能被强制释放
 * 4. 循环等待：ThreadA -> ThreadB -> ThreadA
 * 
 * 排查方法：
 * - jstack -l <pid>：自动检测并报告死锁
 * - jconsole：图形化查看死锁线程
 * 
 * 解决方案：
 * 1. 锁顺序一致：所有线程按相同顺序获取锁
 * 2. 超时机制：使用 tryLock() 设置超时时间
 * 3. 死锁检测：使用 jstack 定期检测
 * 
 * @author Ram Lakshmanan
 */
public class DeadLockDemo {
	
	/**
	 * 启动死锁演示
	 * 
	 * 创建并启动 ThreadA 和 ThreadB，触发死锁
	 */
	public static void start() {
		
		System.out.println("App started");
		new ThreadA().start();
		new ThreadB().start();
	}
}
