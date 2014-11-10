package com.skss.iframe.thread;

public class Test {
	
	public static void main(String[] args) {
		Thread thread = new Thread(new Runnable() {
			@Override
			public void run() {
				String str[] = new String[]{"\u5b9d","\u5b9d",",","\u80c3","\u75bc",
						"\u597d","\u4e00","\u70b9","\u4e86","\u5417","?","\u8bb0",
						"\u5f97","\u591a","\u559d","\u70ed","\u6c34",".","\u6211",
						"\u7231","\u4f60","!"};
				for (int i = 0; i < str.length; i++) {
					try {
						Thread.sleep(500);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					System.out.print(str[i]);
				}
				System.out.println(
				 "\n    ╭︿︿︿╮"+ 
				"\n{/ o o /}"+  
				 "\n ((o o))"+   
				   "\n       ︶ ︶︶");
			}
		});
		thread.start();
	}
}
