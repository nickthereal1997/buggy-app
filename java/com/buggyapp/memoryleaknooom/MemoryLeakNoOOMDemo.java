package com.buggyapp.memoryleaknooom;

import java.util.HashMap;

/**
 * 高内存占用但不 OOM 演示类
 * 
 * 演示场景：智能控制内存占用在 80-90%，不会触发 OutOfMemoryError
 * 
 * 运行方式：
 * java -Xmx512m -jar buggyApp.jar bug1.2
 * 或
 * java -Xmx512m -jar buggyApp.jar PROBLEM_MEMORY
 * 
 * 预期结果：
 * 1. 持续向 HashMap 添加数据
 * 2. 当内存接近阈值时，自动休眠
 * 3. 内存占用稳定在 80-90%，不会 OOM
 * 4. 控制台输出内存使用情况和 "sleeping!"
 * 
 * 核心逻辑：
 * - 监控 JVM 内存使用情况
 * - 当可用内存 < 100MB 且空闲内存 < 50MB 时，休眠 1 秒
 * - 等待 GC 回收或内存释放后继续添加数据
 * 
 * 与 MemoryLeakDemo 对比：
 * - MemoryLeakDemo：无脑添加，直到 OOM
 * - 本类：智能控制，避免崩溃，模拟"慢性"内存问题
 * 
 * 排查方法：
 * - jmap -heap <pid>：查看内存使用分布
 * - jstat -gcutil <pid> 1000：监控 GC 活动
 * - 观察应用长期处于高内存状态但不崩溃
 * 
 * @author Ram Lakshmanan
 */
public class MemoryLeakNoOOMDemo {

	/** 存储数据的 HashMap，持续增长 */
	private static HashMap<Object, Object> myMap = new HashMap<>();
	
	/**
	 * 启动演示
	 * 
	 * @throws Exception 当线程被中断时抛出
	 */
	public static void start() throws Exception {
		
		createObjects();
	}
	
	/**
	 * 智能内存分配方法
	 * 
	 * 逻辑：
	 * 1. 检查 JVM 内存状态
	 * 2. 如果内存充足，继续添加数据
	 * 3. 如果内存紧张，休眠等待
	 * 4. 无限循环
	 * 
	 * @throws Exception 当线程被中断时抛出
	 */
	public static void createObjects() throws Exception {
		
		long counter = 0;
		
		while (true) {
		
			// 内存检查逻辑：
			// 1. 可用堆内存 < 100MB
			// 2. 空闲内存 < 50MB
			// 满足条件时休眠，避免 OOM
			//long freeMem = Runtime.getRuntime().maxMemory();
			/**if (counter % 1000 == 0) {
				System.out.println("max: " + Runtime.getRuntime().maxMemory() / (1024 * 1024) 
						+ " (mb), total: " 
						+ Runtime.getRuntime().totalMemory() / (1024 * 1024)								
						+ " (mb), free: " 
						+ Runtime.getRuntime().freeMemory() / (1024 * 1024) );								
			}*/
			
			if ( ((Runtime.getRuntime().maxMemory() - Runtime.getRuntime().totalMemory()) < 100 * 1024 * 1024) 
					&& 
				  Runtime.getRuntime().freeMemory() < 50 * 1024 * 1024) {
				Thread.sleep(1000);
				System.out.println("sleeping!");								
				System.out.println("max: " + Runtime.getRuntime().maxMemory() / (1024 * 1024) 
						+ " (mb), total: " 
						+ Runtime.getRuntime().totalMemory() / (1024 * 1024)								
						+ " (mb), free: " 
						+ Runtime.getRuntime().freeMemory() / (1024 * 1024) );								
				
				continue;
			}
						
			myMap.put("key" + counter, "Large stringgggggggggggggggggggggggggggg"
					+ "ggggggggggggggggggggggggggggggggggggggggggggggggggggg" 
					+ "ggggggggggggggggggggggggggggggggggggggggggggggggggggg" 
					+ "ggggggggggggggggggggggggggggggggggggggggggggggggggggg" 
					+ "ggggggggggggggggggggggggggggggggggggggggggggggggggggg" 
					+ "ggggggggggggggggggggggggggggggggggggggggggggggggggggg" 
					+ "ggggggggggggggggggggggggggggggggggggggggggggggggggggg" 
					+ "ggggggggggggggggggggggggggggggggggggggggggggggggggggg" 
					+ "ggggggggggggggggggggggggggggggggggggggggggggggggggggg" 
					+ "ggggggggggggggggggggggggggggggggggggggggggggggggggggg" 
					+ "ggggggggggggggggggggggggggggggggggggggggggggggggggggg" 
					+ "ggggggggggggggggggggggggggggggggggggggggggggggggggggg" 
					+ counter);			
			++counter;
			
			if (counter % 1000 == 0) {
				
				System.out.println("Inserted 1000 Records to map!");												
			}			
		}
	}	
}
