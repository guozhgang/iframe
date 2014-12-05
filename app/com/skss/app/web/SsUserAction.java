package com.skss.app.web;

import javax.annotation.Resource;
import com.skss.iframe.web.ActionUtil;
import com.skss.app.entity.SsUser;
import com.skss.app.service.SsUserService;

public class SsUserAction extends ActionUtil<SsUser, SsUserAction>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Resource
	private SsUserService ssUserService;
	public void save() {
		try {
			ssUserService.saveSsUser(model);
			this.sendSuccess();
		} catch (Exception e) {
			// TODO: handle exception
			this.sendMessage(false, "参数错误");
		}
	}
	public void remove() {
		ssUserService.remove(model);
	}
	public void list() {		
		this.sendJSON(ssUserService.list(model, start, rows), ssUserService.count());
	}
}
