package com.buggyapp.cpuspike;

/**
 * CPU 飙升演示 - 工作线程
 * 
 * 职责：执行无限循环，消耗 CPU 资源
 * 
 * 注意：此线程执行的是空循环，没有 sleep 或阻塞操作，
 * 会持续占用 CPU 时间片
 * 
 * @author Ram Lakshmanan
 */
public class CPUSpikerThread extends Thread {

	/**
	 * 线程执行逻辑
	 * 
	 * 调用 Object1.execute() 进入无限循环
	 */
	@Override
	public void run() {
		
		Object1.execute();
	}
}
