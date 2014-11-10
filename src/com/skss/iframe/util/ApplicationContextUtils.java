package com.skss.iframe.util;


import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.skss.app.entity.Menu;
import com.skss.app.service.MenuService;

public class ApplicationContextUtils  implements ServletContextListener{
	private ServletContext application;
	public static  WebApplicationContext applicationContext;
	public void contextDestroyed(ServletContextEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	public void contextInitialized(ServletContextEvent arg0) {
		// TODO Auto-generated method stub
		applicationContext = WebApplicationContextUtils.getWebApplicationContext(arg0.getServletContext());
		//服务器启动时获取工程根目录
		application = arg0.getServletContext();
		String path = application.getContextPath();  
		application.setAttribute("pathdd", path);
		MenuService menuService = (MenuService)applicationContext.getBean("menuService");
		List<Menu> list = menuService.findMenuByAsync(null);
		System.out.println(list.size());
	}
	
	public static Object getBean(String name) {
		return applicationContext.getBean(name);
	}
	
	
}
