package com.buggyapp.oomcrash;

import java.util.UUID;

/**
 * 
 * @author Ram Lakshmanan
 */
public class LimitlessArray {

	/**
	 * 启动超大数组OOM演示
	 * 尝试分配超大数组直接导致OutOfMemoryError
	 */
	public static void start() {
		  
	    System.out.println("The begining");
	    String[] bla = new String[800000000]; //crashes-right away
	    //String[] bla = new String[400000000];
	    
	    for (int i=0; i<400000000; i++) {
	    
	    	bla[i] = UUID.randomUUID().toString();
	    }
	    
	    System.out.println("The end");
	}
	
	public static void main(String[] args) {
		start();
	}
}
