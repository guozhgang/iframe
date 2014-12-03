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

	public List<Menu> findMenuByAsync(String id, String menuIds) {
		String hql = "select t from Menu t where t.id in (" + menuIds
				+ ") and ";
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

	public void saveMenu(Menu menu) {
		menuDao.saveMenu(menu);
	}
}
