package com.skss.iframe.thread;

public class MyThread extends Thread{
	private String name;
	private String val;
	public MyThread(String val) {
		this.val = val;
	}
	public synchronized void printVal() {
		String[] str = new String[]{"马","亚","妮"};
		for (int i = 0; i < str.length; i++) {
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.print(str[i]);
		}
	}
	public void run() {
		printVal();
	}
	
}
