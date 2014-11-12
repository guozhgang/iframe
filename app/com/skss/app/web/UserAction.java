package com.skss.app.web;

import javax.annotation.Resource;

import com.skss.app.entity.User;
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
		try {
			userService.saveUser(model);
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
}
