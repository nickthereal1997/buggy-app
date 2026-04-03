package com.buggyapp.deadlock;

/**
 * 死锁演示 - 资源类 B
 * 
 * 职责：提供同步方法 method2，作为死锁链条的另一部分
 * 
 * 死锁分析：
 * - method2 是 static synchronized，锁对象是 HotObject.class
 * - 方法内部调用 CoolObject.method1()
 * - 如果 ThreadA 已持有 CoolObject 锁，ThreadB 将阻塞
 * 
 * 锁顺序：
 * ThreadA: CoolObject -> HotObject
 * ThreadB: HotObject -> CoolObject（相反顺序导致死锁）
 * 
 * @author Ram Lakshmanan
 */
public class HotObject {

	/**
	 * 同步方法，持有 HotObject.class 锁
	 * 
	 * 执行步骤：
	 * 1. 获取 HotObject.class 锁
	 * 2. 休眠 10 秒（模拟耗时操作）
	 * 3. 调用 CoolObject.method1()，请求 CoolObject.class 锁
	 */
	public static synchronized void method2() {
		
		try {
			// 休眠 10 秒，增加死锁发生概率
			Thread.sleep(10 * 1000);
		} catch (Exception e) {
			
		}
		
		// 尝试获取 CoolObject 锁
		CoolObject.method1();
	}	
}
