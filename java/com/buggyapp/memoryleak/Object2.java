package com.buggyapp.memoryleak;

/**
 * 内存泄漏演示 - 对象链第二层
 * 
 * 职责：作为对象链的中间层，继续委托调用到 Object3
 * 作用：模拟真实应用中多层对象调用的场景，增加对象引用链的复杂度
 * 
 * @author Ram Lakshmanan
 */
public class Object2 {
	
	/** 引用 Object3，继续对象链 */
	Object3 object3 = new Object3();

	/**
	 * 传递内存泄漏操作到下一层
	 */
	public void grow() {

		object3.grow();
	}	
}
