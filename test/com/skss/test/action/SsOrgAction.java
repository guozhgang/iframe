package com.skss.test.action;

import javax.annotation.Resource;
import com.skss.iframe.web.ActionUtil;
import com.skss.test.entity.SsOrg;
import com.skss.test.service.SsOrgService;

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
}
