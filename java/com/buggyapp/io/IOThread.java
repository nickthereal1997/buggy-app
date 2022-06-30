package com.buggyapp.io;

import com.buggyapp.util.FileUtil;

/**
 * 
 * @author Ram Lakshmanan
 */
public class IOThread extends Thread {
	
	public String fileName;

	public static final String CONTENT = 
									  "Hello World! We are building a simple chaos engineering product here. \n"
									+ "Hello World! We are building a simple chaos engineering product here. \n"
									+ "Hello World! We are building a simple chaos engineering product here. \n"
									+ "Hello World! We are building a simple chaos engineering product here. \n"
									+ "Hello World! We are building a simple chaos engineering product here. \n"
									+ "Hello World! We are building a simple chaos engineering product here. \n"
									+ "Hello World! We are building a simple chaos engineering product here. \n"
									+ "Hello World! We are building a simple chaos engineering product here. \n"
									+ "Hello World! We are building a simple chaos engineering product here. \n"
									+ "Hello World! We are building a simple chaos engineering product here. \n"
									+ "Hello World! We are building a simple chaos engineering product here. \n"
									+ "Hello World! We are building a simple chaos engineering product here. \n"
									+ "Hello World! We are building a simple chaos engineering product here. \n"
									+ "Hello World! We are building a simple chaos engineering product here. \n"
									+ "Hello World! We are building a simple chaos engineering product here. \n"
									+ "Hello World! We are building a simple chaos engineering product here. \n"
									+ "Hello World! We are building a simple chaos engineering product here. \n";

	public IOThread(String fileName) {
		this.fileName = fileName;
	}
	
	public void run() {
	
		int counter = 0;
		// Loop infinitely trying to read and close the file.
		while (true) {
			
			// Create a file with name of this thread.
			FileUtil.write(fileName, CONTENT);			
			FileUtil.read(fileName);
			
			if (++counter == 1000) {
				
				System.out.println("Read & write 1000 times to " + fileName);
				counter = 0;
			}
		}
	}
}