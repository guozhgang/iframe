package com.skss.test.dao;

import org.springframework.stereotype.Repository;

import com.skss.test.entity.SsMenu;
import com.skss.iframe.dao.BaseDao;

@Repository
public class SsMenuDao extends BaseDao<SsMenu>{
	public void saveSsMenu(SsMenu ssMenu) {
		this.saveOrUpdate(ssMenu);
	}
	public void remove(SsMenu ssMenu) {
		this.delete(ssMenu);
	}
}
