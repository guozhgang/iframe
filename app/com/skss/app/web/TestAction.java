package com.skss.app.web;

import javax.annotation.Resource;
import com.skss.iframe.web.ActionUtil;
import com.skss.app.entity.Test;
import com.skss.app.service.TestService;

public class TestAction extends ActionUtil<Test, TestAction>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Resource
	private TestService testService;
	public void save() {
		try {
			testService.saveTest(model);
			this.sendSuccess();
		} catch (Exception e) {
			// TODO: handle exception
			this.sendMessage(false, "参数错误");
		}
	}
	public void remove() {
		testService.remove(model);
	}
	public void list() {		
		this.sendJSON(testService.list(model, start, rows), testService.count());
	}
}
