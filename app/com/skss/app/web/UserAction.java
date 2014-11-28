package com.skss.app.web;

import java.util.List;



import javax.annotation.Resource;

import org.apache.struts2.convention.annotation.ResultPath;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.skss.app.entity.Role;
import com.skss.app.entity.User;
import com.skss.app.service.RoleService;
import com.skss.app.service.UserService;
import com.skss.iframe.web.ActionUtil;
@ResultPath("/app")
public class UserAction extends ActionUtil<User,UserAction>{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Resource
	private UserService userService;
	@Resource
	private RoleService roleService;
	public void save() {
		String roleId = request.getParameter("roleId");
		try {
			userService.saveUser(model, roleId);
			logger.info("用户增加成功");
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
	public String login() {
		model = userService.login(model);
		if (model != null) {
			this.session.put("user", model);
			//List<Role> roles = roleService.list(new Role(), start, 100);
			//System.out.println(roles.get(0).getMenuList());
			//this.session.put("role", roles);
			sendMessage(true, "登陆成功");
			return "login";
		} else {
			sendMessage(false, "登陆失败,用户名或密码错误");
			return null;
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
