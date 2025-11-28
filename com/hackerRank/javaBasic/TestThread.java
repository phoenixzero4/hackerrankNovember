package com.hackerRank.javaBasic;

class SampleDemo implements Runnable{
	private Thread t;
	private String threadName;
	
	SampleDemo(String threadName){
		this.threadName = threadName;
	}
	
	public void run() {
		while(true)
			System.out.print(threadName);
	}
	
	public void start() {
		if(t == null) {
			t = new Thread(this, threadName);
			t.start();
		}
	}
}

public class TestThread {

	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		SampleDemo a = new SampleDemo("A");
		SampleDemo b = new SampleDemo("B");
		
	
		a.start();
		b.start();

	}

}
