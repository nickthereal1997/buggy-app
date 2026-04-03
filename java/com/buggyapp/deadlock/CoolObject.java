package com.buggyapp.deadlock;

/**
 * 死锁演示 - 资源类 A
 * 
 * 职责：提供同步方法 method1，作为死锁链条的一部分
 * 
 * 死锁分析：
 * - method1 是 static synchronized，锁对象是 CoolObject.class
 * - 方法内部调用 HotObject.method2()
 * - 如果 ThreadB 已持有 HotObject 锁，ThreadA 将阻塞
 * 
 * 锁顺序：
 * ThreadA: CoolObject -> HotObject
 * ThreadB: HotObject -> CoolObject（相反顺序导致死锁）
 * 
 * @author Ram Lakshmanan
 */
public class CoolObject {

	/**
	 * 同步方法，持有 CoolObject.class 锁
	 * 
	 * 执行步骤：
	 * 1. 获取 CoolObject.class 锁
	 * 2. 休眠 10 秒（模拟耗时操作）
	 * 3. 调用 HotObject.method2()，请求 HotObject.class 锁
	 */
	public static synchronized void method1() {
		
		try {
			// 休眠 10 秒，增加死锁发生概率
			Thread.sleep(10 * 1000);
		} catch (Exception e) {
			
		}
		
		// 尝试获取 HotObject 锁
		HotObject.method2();
	}
}
