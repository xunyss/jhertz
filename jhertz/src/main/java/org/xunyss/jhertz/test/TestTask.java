package org.xunyss.jhertz.test;

public class TestTask implements Runnable {

	private int i = 0;
	
	public TestTask(int i) {
		this.i = i;
	}
	
	@Override
	public void run() {
		System.out.println("Task(" + i + ") is run! " + System.currentTimeMillis());
	}
}
