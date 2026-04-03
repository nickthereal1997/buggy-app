package com.buggyapp.sampleapp;

import static com.buggyapp.sampleapp.SampleAppDemo.THRESHOLD;
import static com.buggyapp.sampleapp.SampleAppDemo.s_map;

/**
 * 消费者线程
 * 
 * 职责：定期清理共享 Map 中的数据，防止内存无限增长
 * 
 * 工作流程：
 * 1. 休眠 1 分钟
 * 2. 清空共享 Map
 * 3. 输出消费日志
 * 4. 重复循环
 * 
 * 与生产者配合：
 * - 生产者持续添加数据
 * - 消费者定期清理数据
 * - 形成平衡，内存使用稳定
 * 
 * @author Ram Lakshmanan
 */
public class Consumer extends Thread {

	/**
	 * 线程执行逻辑
	 * 
	 * 无限循环清理数据
	 */
	@Override
	public void run() {
		
		String data = null;
		long counter = 0;
		while (true) {
			
			try {
			
				// 休眠 1 分钟
				Thread.currentThread().sleep(1 * 60 * 1000);				
			} catch (Exception e) { e.printStackTrace();}
			
			// 清空共享 Map，释放内存
			s_map.clear();
			System.out.println("Consumer thread consumed all Objects");
		}		
	}
	
}
