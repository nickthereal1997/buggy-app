package com.buggyapp.memoryleak;

/**
 * 内存泄漏演示 - 对象链第一层
 * 
 * 职责：作为内存泄漏对象链的入口，委托给 Object2 继续处理
 * 设计模式：责任链模式，通过对象引用链逐层传递调用
 * 
 * 对象链结构：Object1 -> Object2 -> Object3 -> MapManager
 * 
 * @author Ram Lakshmanan
 */
public class Object1 {

	/** 引用 Object2，形成对象链 */
	Object2 object2 = new Object2();
	
	/**
	 * 触发内存泄漏操作
	 * 将调用委托给 Object2，最终由 MapManager 执行实际的内存分配
	 */
	public void grow() {
	
		object2.grow();
	}
}
