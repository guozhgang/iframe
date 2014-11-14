package com.skss.app.web;

import javax.annotation.Resource;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.skss.app.entity.Role;
import com.skss.app.entity.User;
import com.skss.app.service.RoleService;
import com.skss.app.service.UserService;
import com.skss.iframe.web.ActionUtil;
public class UserAction extends ActionUtil<User>{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Resource
	private UserService userService;
	public void save() {
		String roleId = request.getParameter("roleId");
		try {
			userService.saveUser(model, roleId);
			this.sendSuccess();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			this.sendMessage(false, "参数错误");
			e.printStackTrace();
		}
	}
	public void list() {
		sendJSON(userService.pagelist(start, rows), 10);
	}
	@SuppressWarnings("unchecked")
	public void login() {
		model = userService.login(model);
		if (!"".equals(model.getId()) && null != model.getId()) {
			this.session.put("user", model);
			sendMessage(true, "登陆成功");
		} else {
			sendMessage(false, "登陆失败,用户名或密码错误");
		}
	}
	public static void main(String[] args) {
		ClassPathXmlApplicationContext ap = new ClassPathXmlApplicationContext("applicationContext.xml");
		RoleService us = (RoleService)ap.getBean("roleService");
		/*User user = new User();
		user.setLoginName("2");
		user.setPassword("2");*/
		
		System.out.println(us.list(new Role(), 0, 10).get(0).getMenuList());
	}
}
