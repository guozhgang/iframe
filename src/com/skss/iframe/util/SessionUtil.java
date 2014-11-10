package com.skss.iframe.util;

import javax.annotation.Resource;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class SessionUtil {
	@Resource(name = "sessionFactory")
	private SessionFactory sessionFactory;
	
	/**
	 * 获取当前session，在commit之后，不用手动关闭session，会自动关闭
	 * @return
	 */
	public  Session getCurrentSession(){
		return sessionFactory.getCurrentSession();
	}
	/**
	 * 打开新session，在commit之后，必须手动关闭session
	 * @return
	 */
	public Session getSession() {
		return sessionFactory.openSession();
	}
}
