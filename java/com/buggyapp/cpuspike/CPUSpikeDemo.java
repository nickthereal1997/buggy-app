package com.buggyapp.cpuspike;

/**
 * CPU 飙升演示入口类
 * 
 * 演示场景：启动多个线程进行无限空转，导致 CPU 使用率飙升至 100%
 * 
 * 运行方式：
 * java -jar buggyApp.jar bug3
 * 或
 * java -jar buggyApp.jar PROBLEM_CPU
 * 
 * 预期结果：
 * 1. 控制台输出 "6 threads launched!"
 * 2. CPU 使用率迅速上升至接近 100%
 * 3. 应用持续高 CPU 运行
 * 
 * 问题原理：
 * - 6 个线程同时执行无限循环
 * - 循环体内没有 sleep 或 I/O 操作
 * - 线程持续占用 CPU 时间片
 * 
 * 排查方法：
 * - top -H -p <pid>：查看各线程 CPU 使用率
 * - jstack <pid>：找出 RUNNABLE 状态的线程
 * - 使用 async-profiler 生成火焰图分析热点代码
 * 
 * @author Ram Lakshmanan
 */
public class CPUSpikeDemo {

	/**
	 * 启动 CPU 飙升演示
	 * 
	 * 创建并启动 6 个 CPUSpikerThread，每个线程执行无限空转
	 */
	public static void start() {
		
		// 启动 6 个线程，每个都执行无限循环
		new CPUSpikerThread().start();
		new CPUSpikerThread().start();
		new CPUSpikerThread().start();
		new CPUSpikerThread().start();
		new CPUSpikerThread().start();
		new CPUSpikerThread().start();
		System.out.println("6 threads launched!");
	}
}
