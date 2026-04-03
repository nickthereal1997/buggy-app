package com.buggyapp;

import com.buggyapp.blockedapp.BlockedAppDemo;
import com.buggyapp.cpuspike.CPUSpikeDemo;
import com.buggyapp.deadlock.DeadLockDemo;
import com.buggyapp.io.IODemo;
import com.buggyapp.memoryleak.MemoryLeakDemo;
import com.buggyapp.memoryleaknooom.MemoryLeakNoOOMDemo;
import com.buggyapp.memoryleakthread.ThreadMemoryLeakDemo;
import com.buggyapp.sampleapp.SampleAppDemo;
import com.buggyapp.slowfinalize.SlowFinalizeDemo;
import com.buggyapp.stackoverflow.StackOverflowDemo;
import com.buggyapp.threadleak.ThreadLeakDemo;

/**
 * Buggy App 程序入口类
 * 
 * 职责：根据命令行参数选择并启动不同的 JVM 问题演示场景
 * 
 * 可用参数：
 * - sample：正常示例（生产者-消费者模式）
 * - bug1 / PROBLEM_OOM：内存泄漏导致 OOM
 * - bug1.1：线程内存泄漏
 * - bug1.2 / PROBLEM_MEMORY：高内存占用但不 OOM
 * - bug2：Finalizer 问题
 * - bug3 / PROBLEM_CPU：CPU 飙升
 * - bug4 / PROBLEM_THREADLEAK：线程泄漏
 * - bug5 / PROBLEM_BLOCKED：线程阻塞
 * - bug6 / PROBLEM_DEADLOCK：死锁
 * - bug7 / PROBLEM_STACKOVERFLOW：栈溢出
 * - bug8 / PROBLEM_IO：I/O 问题
 * 
 * 运行示例：
 * java -Xmx512m -jar buggyApp.jar bug1
 * 
 * @author Ram Lakshmanan
 */
public class LaunchPad {

	/**
	 * 程序入口
	 * 
	 * @param args 命令行参数，第一个参数指定演示场景
	 */
	public static void main(String args[]) {
		
		if (args == null || args.length < 1) {
			printUsage(args);
			return;
		}
		
		System.out.println("Application started!");

		try {
			
			switch (args[0]) {
			
			case "sample": 
				// 正常示例：生产者线程创建对象，消费者线程删除对象
				// 用法：java -Xmx256m -jar buggyApp.jar sample
				SampleAppDemo.start();
				break;
	
			case "bug1": 
			case "PROBLEM_OOM":				
				// 内存泄漏：HashMap 无限增长，导致 OutOfMemoryError
				// 用法：java -Xmx512m -jar buggyApp.jar bug1
				MemoryLeakDemo.start();
				break;
			
			case "bug1.1": 
				// 线程内存泄漏：在线程中创建无限增长的 HashMap
				// 用法：java -Xmx512m -jar buggyApp.jar bug1.1
				ThreadMemoryLeakDemo.start();
				break;
				
			case "bug1.2":
			case "PROBLEM_MEMORY":
				// 高内存占用：HashMap 增长到占用 80-90% 内存，但不会 OOM
				// 用法：java -Xmx512m -jar buggyApp.jar PROBLEM_MEMORY
				MemoryLeakNoOOMDemo.start();
				break;
				
	
			case "bug2":	
				// Finalizer 问题：使 Finalizer 线程休眠，导致对象堆积
				// 用法：java -Xmx512m -jar buggyApp.jar bug2
				SlowFinalizeDemo.start();
				break;
				
			case "bug3":
			case "PROBLEM_CPU":
				// CPU 飙升：无限循环导致 CPU 使用率 100%
				// 用法：java -Xmx256m -jar buggyApp.jar bug3
				System.out.println("Check out CPU consumption. It might spike up!");
				CPUSpikeDemo.start();
				break;
				
			case "bug4":
			case "PROBLEM_THREADLEAK":
				// 线程泄漏：无限创建线程
				// 用法：java -Xmx1g -jar buggyApp.jar bug4
				ThreadLeakDemo.start();
				break;
	
			case "bug5":
			case "PROBLEM_BLOCKED":				
				// 线程阻塞：使多个线程进入 BLOCKED 状态
				// 用法：java -Xmx1g -jar buggyApp.jar bug5
				BlockedAppDemo.start();
				break;
	
			case "bug6":
			case "PROBLEM_DEADLOCK":				
				// 死锁：创建死锁场景
				// 用法：java -Xmx1g -jar buggyApp.jar bug6
				DeadLockDemo.start();
				break;
	
			case "bug7":
			case "PROBLEM_STACKOVERFLOW":				
				// 栈溢出：创建 StackOverflowError
				// 用法 1：java -Xmx256m -Xss128k -jar buggyApp.jar bug7
				// 用法 2：java -Xmx256m -Xss1m -jar buggyApp.jar bug7
				// 用法 2 更快崩溃，用法 1 运行更久
				StackOverflowDemo demo = new StackOverflowDemo();
				try {
					demo.start();
				} catch (Throwable t) {
					System.out.println("total iterations:" + demo.counter);
					t.printStackTrace();
				}
				break;
				
			case "bug8":
			case "PROBLEM_IO":
				// I/O 问题：模拟 I/O 密集型操作
				IODemo ioDemo = new IODemo();
				ioDemo.start();
				break;
				
			default: 
				printUsage(args);
			}
		} catch (Throwable t) {
			
			System.out.println(t);
			t.printStackTrace();
		}

	}
	
	/**
	 * 打印使用说明
	 * 
	 * @param args 用户输入的参数
	 */
	public static void printUsage(String[] args) {
		
		StringBuilder builder = new StringBuilder("Invalid argument: ");
		for (String arg : args) {
			builder.append(arg)
					.append(" ");
		}
		
		builder.append("\n.Example Usage: java -jar buggyApp.jar bug1");
		System.out.println(builder);
	}
}
