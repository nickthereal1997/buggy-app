package com.buggyapp.references;

public class SimpleExample {

	/**
	 * 启动引用链演示
	 * 创建对象引用链，演示对象间的引用关系
	 */
	public static void start() throws Exception {
	
		System.out.println("Starting References Demo...");
		System.out.println("Creating object reference chain (A -> C -> D/E, B -> C)...");
		
		A a = new A();
		B b = new B();
		
		System.out.println("Objects created!");
		System.out.println("A has reference to C (singleton)");
		System.out.println("B has reference to C (same singleton)");
		System.out.println("C has references to D and E");
		System.out.println("\nUse heap dump tools to analyze object references");
		
		// 保持运行以便进行堆分析
		Thread.sleep(60 * 1000);
	}
	
	public static void main (String argsp[]) throws Exception {
		start();
	}
}
