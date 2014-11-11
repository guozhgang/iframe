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
			try {
				roleService.save(model, menuIds);
				this.sendSuccess();
			} catch (Exception e) {
				// TODO: handle exception
				this.sendMessage(false, "参数错误");
			}
		} else {
			this.sendMessage(false, "角色名称已经存在!");
		}
	}
	public void list() {		
		this.sendJSON(roleService.list(model, start, rows), roleService.count());
	}
	public void findRoleById() {
		Role role = this.roleService.findRoleById("402881f54988f750014988f7ca550002");
		System.out.println("role:"+role.getRoleName());
	}
	public void remove() {
		try {
			this.roleService.remove(model);
			this.sendSuccess();
		} catch (Exception e) {
			// TODO: handle exception
			this.sendFailure();
		}
		
	}
}
