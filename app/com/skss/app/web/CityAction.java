package com.skss.app.web;

import javax.annotation.Resource;
import com.skss.iframe.web.ActionUtil;
import com.skss.app.entity.City;
import com.skss.app.service.CityService;

public class CityAction extends ActionUtil<City, CityAction>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Resource
	private CityService cityService;
	public void save() {
		try {
			cityService.saveCity(model);
			this.sendSuccess();
		} catch (Exception e) {
			// TODO: handle exception
			this.sendMessage(false, "参数错误");
		}
	}
	public void remove() {
		cityService.remove(model);
	}
	public void list() {		
		this.sendJSON(cityService.list(model, start, rows), cityService.count());
	}
}
