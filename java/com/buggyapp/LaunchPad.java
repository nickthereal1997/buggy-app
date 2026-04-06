package com.buggyapp;

import com.buggyapp.blockedapp.BlockedAppDemo;
import com.buggyapp.cpuspike.CPUSpikeDemo;
import com.buggyapp.deadlock.DeadLockDemo;
import com.buggyapp.efficientcode.EfficientExample;
import com.buggyapp.efficientcode.InefficientExample;
import com.buggyapp.efficientcode.RandomExample;
import com.buggyapp.inefficientlist.ArraysDemo;
import com.buggyapp.inefficientlist.ListWithCapacityDemo;
import com.buggyapp.inefficientlist.ListWithOutCapacityDemo;
import com.buggyapp.io.IODemo;
import com.buggyapp.memoryleak.MemoryLeakDemo;
import com.buggyapp.memoryleaknooom.MemoryLeakNoOOMDemo;
import com.buggyapp.memoryleakthread.ThreadMemoryLeakDemo;
import com.buggyapp.metaspaceleak.MetaspaceLeakProgram;
import com.buggyapp.oomcrash.LimitlessArray;
import com.buggyapp.oomcrash.OOMCrash;
import com.buggyapp.oomcrash.OOMNoCrash;
import com.buggyapp.references.SimpleExample;
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
 * - bug9 / PROBLEM_OOMCRASH：OOM崩溃（无限HashMap增长）
 * - bug9.1：OOM但不崩溃（捕获OOM异常）
 * - bug9.2：超大数组OOM
 * - bug10 / PROBLEM_METASPACE：Metaspace泄漏
 * - bug11 / PROBLEM_LIST：List性能对比（无初始容量）
 * - bug11.1：List性能对比（有初始容量）
 * - bug11.2：数组性能对比
 * - bug12 / PROBLEM_EFFICIENT：高效代码示例（延迟初始化）
 * - bug12.1：低效代码示例（立即初始化）
 * - bug12.2：随机低效代码示例
 * - bug13 / PROBLEM_REFERENCES：对象引用链演示
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
						
			case "bug9":
			case "PROBLEM_OOMCRASH":
				// OOM崩溃：无限HashMap增长导致OutOfMemoryError
				// 用法：java -Xmx512m -jar buggyApp.jar bug9
				OOMCrash.start();
				break;
						
			case "bug9.1":
				// OOM但不崩溃：捕获OutOfMemoryError后应用继续运行
				// 用法：java -Xmx512m -jar buggyApp.jar bug9.1
				OOMNoCrash.start();
				break;
						
			case "bug9.2":
				// 超大数组OOM：直接分配超大数组导致OOM
				// 用法：java -Xmx512m -jar buggyApp.jar bug9.2
				LimitlessArray.start();
				break;
						
			case "bug10":
			case "PROBLEM_METASPACE":
				// Metaspace泄漏：持续创建类加载器导致Metaspace溢出
				// 用法：java -XX:MaxMetaspaceSize=64m -jar buggyApp.jar bug10
				MetaspaceLeakProgram.start();
				break;
						
			case "bug11":
			case "PROBLEM_LIST":
				// List无初始容量：演示ArrayList扩容开销
				// 用法：java -jar buggyApp.jar bug11
				ListWithOutCapacityDemo.start();
				break;
						
			case "bug11.1":
				// List有初始容量：演示指定初始容量的性能优势
				// 用法：java -jar buggyApp.jar bug11.1
				ListWithCapacityDemo.start();
				break;
						
			case "bug11.2":
				// 数组性能：对比ArrayList与原生数组
				// 用法：java -jar buggyApp.jar bug11.2
				ArraysDemo.start();
				break;
						
			case "bug12":
			case "PROBLEM_EFFICIENT":
				// 高效代码：延迟初始化示例
				// 用法：java -jar buggyApp.jar bug12
				EfficientExample.start();
				break;
						
			case "bug12.1":
				// 低效代码：立即初始化开销示例
				// 用法：java -jar buggyApp.jar bug12.1
				InefficientExample.start();
				break;
						
			case "bug12.2":
				// 随机低效代码：常见编码效率问题
				// 用法：java -jar buggyApp.jar bug12.2
				RandomExample.start();
				break;
						
			case "bug13":
			case "PROBLEM_REFERENCES":
				// 对象引用链：演示对象间引用关系
				// 用法：java -jar buggyApp.jar bug13
				SimpleExample.start();
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
			builder.append(arg).append(" ");
		}
		
		builder.append("\n\nAvailable options:\n");
		builder.append("  sample          - 正常示例（生产者-消费者模式）\n");
		builder.append("  bug1/PROBLEM_OOM       - 内存泄漏导致OOM\n");
		builder.append("  bug1.1                 - 线程内存泄漏\n");
		builder.append("  bug1.2/PROBLEM_MEMORY  - 高内存占用但不OOM\n");
		builder.append("  bug2                   - Finalizer问题\n");
		builder.append("  bug3/PROBLEM_CPU       - CPU飙升\n");
		builder.append("  bug4/PROBLEM_THREADLEAK- 线程泄漏\n");
		builder.append("  bug5/PROBLEM_BLOCKED   - 线程阻塞\n");
		builder.append("  bug6/PROBLEM_DEADLOCK  - 死锁\n");
		builder.append("  bug7/PROBLEM_STACKOVERFLOW - 栈溢出\n");
		builder.append("  bug8/PROBLEM_IO        - I/O问题\n");
		builder.append("  bug9/PROBLEM_OOMCRASH  - OOM崩溃\n");
		builder.append("  bug9.1                 - OOM但不崩溃\n");
		builder.append("  bug9.2                 - 超大数组OOM\n");
		builder.append("  bug10/PROBLEM_METASPACE- Metaspace泄漏\n");
		builder.append("  bug11/PROBLEM_LIST     - List无初始容量\n");
		builder.append("  bug11.1                - List有初始容量\n");
		builder.append("  bug11.2                - 数组性能对比\n");
		builder.append("  bug12/PROBLEM_EFFICIENT- 高效代码示例\n");
		builder.append("  bug12.1                - 低效代码示例\n");
		builder.append("  bug12.2                - 随机低效代码\n");
		builder.append("  bug13/PROBLEM_REFERENCES- 对象引用链\n");
		builder.append("\nExample Usage: java -jar buggyApp.jar bug1");
		System.out.println(builder);
	}
}
