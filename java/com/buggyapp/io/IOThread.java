package com.buggyapp.io;

import com.buggyapp.util.FileUtil;

/**
 * I/O 演示 - 文件读写线程
 * 
 * 职责：持续对指定文件进行读写操作，模拟 I/O 密集型任务
 * 
 * 工作流程：
 * 1. 向文件写入固定内容
 * 2. 从文件读取内容
 * 3. 每 1000 次读写输出日志
 * 4. 无限循环
 * 
 * 注意：高频文件读写会占用大量磁盘 I/O 资源
 * 
 * @author Ram Lakshmanan
 */
public class IOThread extends Thread {
	
	/** 目标文件名 */
	public String fileName;

	/** 写入文件的固定内容 */
	public static final String CONTENT = 
									  "Hello World! We are building a simple chaos engineering product here. \n"
									+ "Hello World! We are building a simple chaos engineering product here. \n"
									+ "Hello World! We are building a simple chaos engineering product here. \n"
									+ "Hello World! We are building a simple chaos engineering product here. \n"
									+ "Hello World! We are building a simple chaos engineering product here. \n"
									+ "Hello World! We are building a simple chaos engineering product here. \n"
									+ "Hello World! We are building a simple chaos engineering product here. \n"
									+ "Hello World! We are building a simple chaos engineering product here. \n"
									+ "Hello World! We are building a simple chaos engineering product here. \n"
									+ "Hello World! We are building a simple chaos engineering product here. \n"
									+ "Hello World! We are building a simple chaos engineering product here. \n"
									+ "Hello World! We are building a simple chaos engineering product here. \n"
									+ "Hello World! We are building a simple chaos engineering product here. \n"
									+ "Hello World! We are building a simple chaos engineering product here. \n"
									+ "Hello World! We are building a simple chaos engineering product here. \n"
									+ "Hello World! We are building a simple chaos engineering product here. \n"
									+ "Hello World! We are building a simple chaos engineering product here. \n";

	/**
	 * 构造方法
	 * @param fileName 目标文件名
	 */
	public IOThread(String fileName) {
		this.fileName = fileName;
	}
	
	/**
	 * 线程执行逻辑
	 * 
	 * 无限循环进行文件读写
	 */
	public void run() {
	
		int counter = 0;
		// 无限循环读写文件
		while (true) {
			
			// 写入文件
			FileUtil.write(fileName, CONTENT);			
			// 读取文件
			FileUtil.read(fileName);
			
			if (++counter == 1000) {
				
				System.out.println("Read & write 1000 times to " + fileName);
				counter = 0;
			}
		}
	}
}