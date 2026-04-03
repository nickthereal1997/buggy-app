package com.buggyapp.io;

/**
 * I/O 问题演示入口类
 * 
 * 演示场景：启动多个线程进行高频文件读写操作，模拟 I/O 密集型应用
 * 
 * 运行方式：
 * java -jar buggyApp.jar bug8
 * 或
 * java -jar buggyApp.jar PROBLEM_IO
 * 
 * 预期结果：
 * 1. 创建 5 个 IOThread 线程
 * 2. 每个线程持续读写对应的文件
 * 3. 控制台定期输出读写次数
 * 
 * 用途：
 * - 演示 I/O 密集型应用的特征
 * - 用于练习 I/O 性能分析
 * - 模拟磁盘瓶颈场景
 * 
 * 排查方法：
 * - iostat -x 1：查看磁盘 I/O 使用率
 * - jstack <pid>：查看线程状态（大量 RUNNABLE 但等待 I/O）
 * 
 * @author Ram Lakshmanan
 */
public class IODemo {

	/**
	 * 启动 I/O 演示
	 * 
	 * 创建 5 个 IOThread，每个线程负责一个文件的读写
	 */
	public void start() {
		
		for (int counter = 1; counter <= 5; ++counter) {

			// 启动 5 个 I/O 线程
			new IOThread("fileIO-" + counter + ".txt").start();
			System.out.println("Starting to write to fileIO-" + counter + ".txt");
		}
	}
}
