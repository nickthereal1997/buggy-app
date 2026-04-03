package com.buggyapp.memoryleak;

/**
 * 内存泄漏演示 - 对象链第三层
 * 
 * 职责：对象链的最后一层，持有并调用 MapManager 执行实际的内存泄漏操作
 * 作用：完成从对象链到实际内存分配逻辑的转换
 * 
 * @author Ram Lakshmanan
 */
public class Object3 {

	/** 实际的内存管理器，负责执行内存泄漏操作 */
	MapManager mapManager = new MapManager();

	/**
	 * 触发 MapManager 执行内存泄漏
	 */
	public void grow() {
		
		mapManager.grow();
	}		
}
