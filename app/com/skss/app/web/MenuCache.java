package com.skss.app.web;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import com.skss.app.entity.Menu;
import com.skss.app.service.MenuService;
import com.skss.iframe.entity.TreeNode;
import com.skss.iframe.web.ActionUtil;

/**
 * 菜单缓存类，服务器启动的时候加载所有菜单
 * @author： guozhigang
 * @time：2014年11月10日,下午5:05:23
 */
public class MenuCache extends ActionUtil<Menu> implements ServletContextListener{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	

	@Override
	public void contextInitialized(ServletContextEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	
	

}
