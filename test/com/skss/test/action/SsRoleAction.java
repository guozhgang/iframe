package com.skss.test.action;

import javax.annotation.Resource;
import com.skss.iframe.web.ActionUtil;
import com.skss.test.entity.SsRole;
import com.skss.test.service.SsRoleService;

public class SsRoleAction extends ActionUtil<SsRole, SsRoleAction>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Resource
	private SsRoleService ssRoleService;
	public void save() {
		try {
			ssRoleService.saveSsRole(model);
			this.sendSuccess();
		} catch (Exception e) {
			// TODO: handle exception
			this.sendMessage(false, "参数错误");
		}
	}
	public void remove() {
		ssRoleService.remove(model);
	}
}
