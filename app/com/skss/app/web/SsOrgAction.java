package com.skss.app.web;

import javax.annotation.Resource;
import com.skss.iframe.web.ActionUtil;
import com.skss.app.entity.SsOrg;
import com.skss.app.service.SsOrgService;

public class SsOrgAction extends ActionUtil<SsOrg, SsOrgAction>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Resource
	private SsOrgService ssOrgService;
	public void save() {
		try {
			ssOrgService.saveSsOrg(model);
			this.sendSuccess();
		} catch (Exception e) {
			// TODO: handle exception
			this.sendMessage(false, "参数错误");
		}
	}
	public void remove() {
		ssOrgService.remove(model);
	}
	public void list() {		
		this.sendJSON(ssOrgService.list(model, start, rows), ssOrgService.count());
	}
}
