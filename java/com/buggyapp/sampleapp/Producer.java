package com.buggyapp.sampleapp;

import static com.buggyapp.sampleapp.SampleAppDemo.*;

/**
 * 生产者线程
 * 
 * 职责：持续向共享 Map 中添加数据
 * 
 * 工作流程：
 * 1. 创建大字符串数据
 * 2. 休眠 1ms（控制生产速度）
 * 3. 将数据放入共享 Map
 * 4. 每生产 THRESHOLD 条数据输出日志
 * 
 * 注意：本类只负责生产，不负责清理
 * 清理工作由 Consumer 线程负责
 * 
 * @author Ram Lakshmanan
 */
public class Producer extends Thread {

	/**
	 * 线程执行逻辑
	 * 
	 * 无限循环生产数据
	 */
	@Override
	public void run() {
		
		String data = null;
		long counter = 0;
		while (true) {
			
			// 创建大字符串数据
			data = "sample app creating large sampleeeeeeeeeeeeeeeeeeeeeeeeeeeee"
					+ "eeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeee"
					+ "eeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeee"
					+ "eeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeee"
					+ "eeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeee"
					+ "eeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeee"
					+ "eeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeee"
					+ "eeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeee"
					+ "eeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeee"
					+ "eeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeee"
					+ "eeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeee"
					+ "eeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeee"
					+ "eeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeee"
					+ "eeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeee"
					+ "eeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeee"
					+ "eeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeee"
					+ "eeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeee"
					+ "eeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeee"
					+ "eeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeee"
					+ "eeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeee"
					+ "eeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeee"
					+ "eeeeeeeeeeeeeeeeeeeeeeeeeee object" + ++counter;
			
			try {
			
				// 短暂休眠，控制生产速度
				Thread.currentThread().sleep(1);				
			} catch (Exception e) { e.printStackTrace();}
			
			// 将数据放入共享 Map
			s_map.put(counter, data);
			if ((counter % THRESHOLD) == 0) {
				System.out.println(getName() + " created " + THRESHOLD + " objects!");
				// s_map.clear();  // 注释掉的清理代码（由 Consumer 负责）
			}
		}		
	}
}
