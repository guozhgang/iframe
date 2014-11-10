package com.skss.app.web;

import javax.annotation.Resource;

import com.skss.app.entity.Role;
import com.skss.app.service.RoleService;
import com.skss.iframe.web.ActionUtil;

public class RoleAction extends ActionUtil<Role>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Resource
	private RoleService roleService;
	public void save() {
		String menuIds = request.getParameter("menuIds");
		Role role = roleService.findRoleByName(model);
		if (role == null) {
			roleService.save(model, menuIds);
		} else {
			this.sendMessage(false, "角色名称已经存在!");
		}
		if (null != model.getId()) {
			this.sendSuccess();
		} else {
			this.sendFailure();
		}
	}
	public void list() {
		this.sendJSON(roleService.list(start, rows), roleService.count());
	}
	public void remove() {
		this.roleService.remove(model);
		this.sendSuccess();
	}
}
