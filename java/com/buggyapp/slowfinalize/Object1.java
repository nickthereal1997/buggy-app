package com.buggyapp.slowfinalize;

/**
 * Finalizer 问题演示类
 * 
 * 演示场景：慢速 finalize() 方法导致 Finalizer 线程堆积，对象无法及时回收
 * 
 * 问题原理：
 * 1. JVM 使用 Finalizer 线程执行对象的 finalize() 方法
 * 2. 本类的 finalize() 休眠 3 分钟，严重拖慢 Finalizer 线程
 * 3. 大量对象在 Finalizer 队列中堆积，等待执行 finalize()
 * 4. 这些对象虽然已死亡，但无法被 GC 回收，导致内存泄漏
 * 
 * 注意：从 Java 9 开始，finalize() 已被标记为废弃（@Deprecated），
 * 推荐使用 try-with-resources 或 Cleaner 类替代
 * 
 * 排查方法：
 * - jmap -heap <pid>：查看堆中等待 finalization 的对象数量
 * - jstack <pid>：查看 Finalizer 线程的堆栈
 * 
 * @author Ram Lakshmanan
 * @deprecated finalize() 方法已废弃，仅用于教学演示
 */
public class Object1 {

	/** 对象持有的数据 */
	public String data;
	
	/**
	 * 构造方法
	 * @param data 对象数据
	 */
	public Object1(String data) {
		
		this.data = data;
	}
	
	/**
	 * 慢速 finalize 方法
	 * 
	 * 警告：此方法休眠 3 分钟，会严重阻塞 Finalizer 线程！
	 * 在实际应用中应避免在 finalize() 中执行耗时操作
	 */
	@Override
	public void finalize() {
	
		try {
			// 休眠 3 分钟，模拟耗时清理操作
			Thread.currentThread().sleep(3 * 60 * 1000);
		} catch (Exception e) {}
	}		
}
