package com.skss.test.action;

import javax.annotation.Resource;
import com.skss.iframe.web.ActionUtil;
import com.skss.test.entity.SsMenu;
import com.skss.test.service.SsMenuService;

public class SsMenuAction extends ActionUtil<SsMenu, SsMenuAction>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Resource
	private SsMenuService ssMenuService;
	public void save() {
		try {
			ssMenuService.saveSsMenu(model);
			this.sendSuccess();
		} catch (Exception e) {
			// TODO: handle exception
			this.sendMessage(false, "参数错误");
		}
	}
	public void remove() {
		ssMenuService.remove(model);
	}
}
