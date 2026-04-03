package com.buggyapp.memoryleak;

/**
 * 内存泄漏演示入口类
 * 
 * 演示场景：HashMap 无限增长导致的内存泄漏，最终触发 OutOfMemoryError
 * 
 * 运行方式：
 * java -Xmx512m -jar buggyApp.jar bug1
 * 
 * 预期结果：
 * 1. 控制台持续输出 "Inserted 1000 Records to map!"
 * 2. 一段时间后抛出 java.lang.OutOfMemoryError: Java heap space
 * 3. 应用继续运行 5 秒后退出
 * 
 * 排查工具：
 * - jmap -heap <pid>：查看堆内存使用情况
 * - jmap -dump:format=b,file=heap.hprof <pid>：生成堆转储文件
 * - jstat -gcutil <pid> 1000：监控 GC 情况
 * 
 * @author Ram Lakshmanan
 */
public class MemoryLeakDemo {

	/** 静态持有 Object1，确保对象链不会被 GC 回收 */
	static Object1 object1 = new Object1();

	/**
	 * 启动内存泄漏演示
	 * 
	 * 执行流程：
	 * 1. 调用 object1.grow() 触发内存泄漏
	 * 2. 捕获 OOM 异常并打印
	 * 3. 休眠 5 秒后退出
	 */
	public static void start() {
	
		try {
			object1.grow();
		} catch (Throwable e) {
			System.out.println(e);
		}
		
		System.out.println("Application is still running :-)");
		try {
			Thread.sleep(5 * 1000);
		} catch (Exception e) {
		}
		
		System.out.println("Application terminated only now :-)");		
	}
}
