package com.skss.test.action;

import javax.annotation.Resource;
import com.skss.iframe.web.ActionUtil;
import com.skss.test.entity.SsUserRole;
import com.skss.test.service.SsUserRoleService;

public class SsUserRoleAction extends ActionUtil<SsUserRole, SsUserRoleAction>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Resource
	private SsUserRoleService ssUserRoleService;
	public void save() {
		try {
			ssUserRoleService.saveSsUserRole(model);
			this.sendSuccess();
		} catch (Exception e) {
			// TODO: handle exception
			this.sendMessage(false, "参数错误");
		}
	}
	public void remove() {
		ssUserRoleService.remove(model);
	}
}
