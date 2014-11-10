package com.skss.app.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.skss.app.dao.MenuDao;
import com.skss.app.entity.Menu;

@Service("menuService")
public class MenuService {
	@Resource
	private MenuDao menuDao;
	public List<Menu> findMenuByAsync(String id) {
		String hql = "from Menu t where ";
		if (id == null) {
			hql += "t.parent = ''";
		} else {
			hql += "t.parent = " + id;
		}
		return menuDao.findMenuByAsync(hql);
	}
	public int findCount(String id) {
		return menuDao.findCount(id);
	}
}
