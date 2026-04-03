package com.buggyapp.slowfinalize;

/**
 * Finalizer 问题演示入口类
 * 
 * 演示场景：无限创建带有慢速 finalize() 的对象，导致 Finalizer 队列堆积
 * 
 * 运行方式：
 * java -jar buggyApp.jar bug2
 * 
 * 预期结果：
 * 1. 控制台持续输出 "created: X"
 * 2. 对象创建速度逐渐变慢
 * 3. 堆内存不断增长，最终可能导致 OOM
 * 
 * 问题分析：
 * - 每个 Object1 实例创建后很快失去引用（成为垃圾）
 * - 但 JVM 必须等待 Finalizer 线程执行其 finalize() 方法
 * - 由于 finalize() 休眠 3 分钟，Finalizer 线程处理速度极慢
 * - 大量对象在 Finalizer 队列中等待，无法被回收
 * 
 * 最佳实践：
 * - 避免使用 finalize()，改用 try-with-resources
 * - 如需清理资源，使用 java.lang.ref.Cleaner（Java 9+）
 * 
 * @author Ram Lakshmanan
 */
public class SlowFinalizeDemo {

	/**
	 * 启动 Finalizer 问题演示
	 * 
	 * 无限循环创建 Object1 实例，触发 Finalizer 队列堆积
	 */
	public static void start() {
		
		long counter = 0;
		
		while (true) {
		
			// 创建对象后立即失去引用，等待 GC
			// 但 finalize() 会阻塞 Finalizer 线程
			new Object1("my-fun-data-" + counter);
			System.out.println("created: " + counter++);
		}
	}	
}
