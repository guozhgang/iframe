package com.skss.iframe.single;

/**
 * 单例模式
 * @author： guozhigang
 * @time：2014年11月24日,上午11:19:11
 */
public class Singleton {
	String name;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	private static Singleton uniqueInstance = null;
	private Singleton() {}
	public static Singleton getInstance() {
		if (uniqueInstance == null) {
			uniqueInstance = new Singleton();
		}
		return uniqueInstance;
	}
	public void info() {
		System.out.println("this name:"+name);
	}
}
