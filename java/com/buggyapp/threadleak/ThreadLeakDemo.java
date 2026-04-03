package com.buggyapp.threadleak;

/**
 * 线程泄漏演示入口类
 * 
 * 演示场景：无限创建线程，每个线程长时间存活但不退出，导致线程数暴增
 * 
 * 运行方式：
 * java -Xmx1g -jar buggyApp.jar bug4
 * 或
 * java -Xmx1g -jar buggyApp.jar PROBLEM_THREADLEAK
 * 
 * 预期结果：
 * 1. 控制台输出 "Thread App started"
 * 2. 线程数量持续增长
 * 3. 最终可能导致 OOM: unable to create new native thread
 * 
 * 问题原理：
 * - 主线程每 100ms 创建一个新线程
 * - 每个 ForeverThread 休眠 10 分钟才结束
 * - 线程创建速度远大于销毁速度
 * - 系统线程资源被耗尽
 * 
 * 排查方法：
 * - jstack <pid> | grep -c "java.lang.Thread.State"：统计线程数
 * - top -H：查看系统线程数量
 * - jvisualvm：图形化查看线程增长趋势
 * 
 * 解决方案：
 * - 使用线程池（ThreadPoolExecutor）管理线程
 * - 设置合理的线程池大小
 * - 使用有界队列防止无限堆积
 * 
 * @author Ram Lakshmanan
 */
public class ThreadLeakDemo {

	/**
	 * 启动线程泄漏演示
	 * 
	 * 无限循环创建 ForeverThread，每 100ms 创建一个
	 */
	public static void start() {
		
		System.out.println("Thread App started");
		while (true) {
			
			try {
			
				// 短暂休眠，控制创建速度
				Thread.sleep(100);
			} catch (Exception e) {				
			}
			
			// 创建并启动新线程
			// 注意：ForeverThread 会存活 10 分钟
			new ForeverThread().start();
		}
	}
}
