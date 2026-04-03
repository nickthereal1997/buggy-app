package com.buggyapp.cpuspike;

/**
 * CPU 飙升演示 - 核心逻辑类
 * 
 * 职责：提供无限循环方法，持续消耗 CPU 资源
 * 
 * 问题分析：
 * - execute() 方法中的 while(true) 是无限循环
 * - 循环体内调用 doSomething()，但 doSomething() 是空方法
 * - 没有 Thread.sleep() 或 I/O 操作让出 CPU
 * - 导致线程持续占用 CPU 时间片
 * 
 * 优化建议：
 * - 在循环中添加适当的 sleep，如 Thread.sleep(10)
 * - 或使用 ScheduledExecutorService 替代无限循环
 * 
 * @author Ram Lakshmanan
 */
public class Object1 {
	
	/**
	 * 执行无限循环，持续消耗 CPU
	 * 
	 * 警告：此方法会导致调用线程占用 100% CPU！
	 */
	public static void execute() {
		
		while (true) {
		
			doSomething();
		}		
	}
	
	/**
	 * 空方法，仅用于演示
	 * 
	 * 在实际场景中，这里可能是业务逻辑处理
	 */
	public static void doSomething() {
		
	}
}
