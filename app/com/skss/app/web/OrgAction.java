package com.skss.app.web;

import javax.annotation.Resource;

import com.skss.app.entity.Organization;
import com.skss.app.service.OrgService;
import com.skss.iframe.web.ActionUtil;


public class OrgAction extends ActionUtil<Organization>{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Resource
	private OrgService orgService;
	public void save() {
		try {
			this.orgService.save(model);
			this.sendSuccess();
		} catch (Exception e) {
			// TODO: handle exception
			this.sendMessage(false, "参数错误");
			e.printStackTrace();
		}
	}
}
