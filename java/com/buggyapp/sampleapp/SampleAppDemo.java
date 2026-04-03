package com.buggyapp.sampleapp;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 正常应用示例 - 生产者消费者模式
 * 
 * 演示场景：展示正确的多线程协作模式，生产者创建对象，消费者清理对象
 * 
 * 运行方式：
 * java -Xmx256m -jar buggyApp.jar sample
 * 
 * 预期结果：
 * 1. 5 个生产者线程持续向共享 Map 添加数据
 * 2. 消费者线程每分钟清理一次 Map
 * 3. 内存使用保持稳定，不会 OOM
 * 4. 应用持续正常运行
 * 
 * 设计要点：
 * - 使用 ConcurrentHashMap 保证线程安全
 * - 生产者定期生产数据
 * - 消费者定期清理数据
 * - 形成平衡，避免内存无限增长
 * 
 * 与内存泄漏对比：
 * - 本示例：有消费者清理，内存稳定
 * - MemoryLeakDemo：无清理，内存泄漏
 * 
 * @author Ram Lakshmanan
 */
public class SampleAppDemo {

	/** 生产者每生产多少条数据输出一次日志 */
	public static int THRESHOLD = 1000;
	
	/** 共享数据存储，使用 ConcurrentHashMap 保证线程安全 */
	public static Map<Long, String> s_map = new ConcurrentHashMap<>();
	
	/**
	 * 启动生产者消费者演示
	 * 
	 * 创建 5 个生产者线程和 1 个消费者线程
	 */
	public static void start() {
		
		try {
					
			// 创建并启动 5 个生产者线程
			for (int counter = 1; counter <= 5; ++counter) {
				
				Producer producer = new Producer();
				producer.setName("Producer-" + counter);
				producer.start();
			}
			
			// 创建并启动消费者线程
			Consumer consumer = new Consumer();
			consumer.setName("Consumer");
			consumer.start();
		} catch (Throwable t) {
			
			System.out.println("SampleAppDemo Failed!!");
			t.printStackTrace();
		}		
	}
}
