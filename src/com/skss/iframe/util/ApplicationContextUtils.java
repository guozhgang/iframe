package com.skss.iframe.util;



import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;


public class ApplicationContextUtils  implements ServletContextListener{
	private static ServletContext application;
	public  static  WebApplicationContext applicationContext ;
	public static Map<String, String> map = new HashMap<String, String>();
	public static String str ;
	public void contextDestroyed(ServletContextEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	public void contextInitialized(ServletContextEvent arg0) {
		// TODO Auto-generated method stub
		application = arg0.getServletContext();
		applicationContext = WebApplicationContextUtils.getWebApplicationContext(application);
		
		System.out.println(WebApplicationContextUtils.getWebApplicationContext(application));
		//applicationContext = (WebApplicationContext)arg0.getServletContext().getAttribute(WebApplicationContext.ROOT_WEB_APPLICATION_CONTEXT_ATTRIBUTE);
		//服务器启动时获取工程根目录
		String path = application.getContextPath();  
		application.setAttribute("path", path);
		application.setAttribute("application", "oooo");
	}
	
	public static Object getBean(String name) {
		System.out.println(application.getAttribute("application"));
		return applicationContext.getBean(name);
	}
	public static void main(String[] args) {
		
	}
	
}
