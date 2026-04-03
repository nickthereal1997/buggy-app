package com.buggyapp.stackoverflow;

import java.util.ArrayList;
import java.util.List;

/**
 * 栈溢出演示类
 * 
 * 演示场景：无限递归调用导致 StackOverflowError
 * 
 * 运行方式：
 * java -Xmx256m -Xss128k -jar buggyApp.jar bug7
 * 或
 * java -Xmx256m -Xss1m -jar buggyApp.jar bug7
 * 
 * 预期结果：
 * 1. 控制台输出递归次数（每 1000 次输出一次）
 * 2. 最终抛出 java.lang.StackOverflowError
 * 3. 栈大小（-Xss）越小，越早触发错误
 * 
 * 问题原理：
 * - start() 方法无限递归调用自身
 * - 每次调用在栈上创建新的栈帧（Stack Frame）
 * - 栈空间耗尽后抛出 StackOverflowError
 * 
 * JVM 参数说明：
 * - -Xss128k：设置线程栈大小为 128KB，更快触发错误
 * - -Xss1m：设置线程栈大小为 1MB，运行更久
 * 
 * 排查方法：
 * - jstack <pid>：查看栈深度
 * - 异常堆栈：观察重复的调用链
 * 
 * 解决方案：
 * 1. 添加递归终止条件
 * 2. 使用循环替代递归
 * 3. 增加栈空间（治标不治本）
 * 
 * @author Ram Lakshmanan
 */
public class StackOverflowDemo {

	/** 递归计数器 */
	public int counter = 0;
	
	/**
	 * 递归方法，无限调用自身
	 * 
	 * 警告：此方法会导致栈溢出！
	 * 
	 * 递归过程：
	 * 1. counter 自增
	 * 2. 每 1000 次输出日志
	 * 3. 再次调用 start()，创建新的栈帧
	 * 4. 重复直到栈空间耗尽
	 */
	public void start() {
		
		++counter;

		// SimpleObject so0 = new SimpleObject("Simple Object created");

		if (counter % 1000 == 0) {
			
			System.out.println("Looped " + counter + " times");
		}
		
		// 无限递归，没有终止条件
		start();		
	}
	
}
