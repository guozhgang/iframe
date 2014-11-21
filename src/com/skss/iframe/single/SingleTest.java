package com.skss.iframe.single;

public class SingleTest {
	public static void main(String[] args) {
		Singleton single = Singleton.getInstance();
		single.setName("001");
		single.setName("002");
		single.setName("003");
		single.info();
	}
}
