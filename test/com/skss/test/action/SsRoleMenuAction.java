package com.skss.test.action;

import javax.annotation.Resource;
import com.skss.iframe.web.ActionUtil;
import com.skss.test.entity.SsRoleMenu;
import com.skss.test.service.SsRoleMenuService;

public class SsRoleMenuAction extends ActionUtil<SsRoleMenu, SsRoleMenuAction>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Resource
	private SsRoleMenuService ssRoleMenuService;
	public void save() {
		try {
			ssRoleMenuService.saveSsRoleMenu(model);
			this.sendSuccess();
		} catch (Exception e) {
			// TODO: handle exception
			this.sendMessage(false, "参数错误");
		}
	}
	public void remove() {
		ssRoleMenuService.remove(model);
	}
}
