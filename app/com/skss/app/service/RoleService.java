package com.skss.app.service;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.skss.app.dao.MenuDao;
import com.skss.app.dao.RoleDao;
import com.skss.app.entity.Menu;
import com.skss.app.entity.Role;

@Service
public class RoleService {
	@Resource
	private RoleDao roleDao;
	@Resource
	private MenuDao menuDao;

	public void save(Role role, String menuIds) {
		String menus[] = menuIds.split(",");
		if (menus.length > 0) {
			List<Menu> menuList = new ArrayList<Menu>();
			for (int i = 0; i < menus.length; i++) {
				menuList.add(this.menuDao.get(menus[i]));
			}
			role.setMenuList(menuList);
		}
		roleDao.saveRole(role);
	}
	
	/**
	 * 角色不能重复
	 * @param role
	 * @return
	 */
	public Role findRoleByName(Role role) {
		String hql = "from Role r where r.roleName = '"+role.getRoleName()+"'";
		if (null == role.getId()) {
			hql += " and r.id != '" + role.getId() + "'";
		}
		List<Role> roles = roleDao.executeByHQL(hql);
		return roles.size() == 0 ? null : roles.get(0);
	}
	
	/**
	 * 分页查询
	 * @param start
	 * @param limit
	 * @return
	 */
	public List<Role> list(int start, int limit) {
		return roleDao.findPaginationListByHQL("from Role", start, limit);
	}
	/**
	 * 统计总数
	 * @return
	 */
	public int count() {
		return roleDao.getCountByHql("select count(i) from Role i");
	}
}
