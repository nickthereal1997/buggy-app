package com.buggyapp.threadleak;

/**
 * 线程泄漏演示 - 永久存活线程
 * 
 * 职责：创建一个长时间休眠但不退出的线程
 * 
 * 问题分析：
 * - 线程启动后进入无限循环
 * - 每次循环休眠 10 分钟
 * - 线程永远不会自然结束
 * - 大量此类线程会导致系统资源耗尽
 * 
 * 与线程池对比：
 * - 正常做法：使用线程池，复用线程
 * - 本类做法：每次新建线程，永不复用
 * 
 * @author Ram Lakshmanan
 */
public class ForeverThread extends Thread {

	/**
	 * 线程执行逻辑
	 * 
	 * 无限循环，每次休眠 10 分钟
	 * 此线程永远不会退出
	 */
	@Override
	public void run() {
	
		while (true) {
		
			try {
				
				Thread.sleep(10 * 60 * 1000);  // 休眠 10 分钟
			} catch (Exception e) {}
		}
	}
}
