package com.skss.app.service;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Service;

import com.skss.app.dao.MenuDao;
import com.skss.app.dao.RoleDao;
import com.skss.app.entity.Menu;
import com.skss.app.entity.Role;
import com.skss.iframe.util.ApplicationContextUtils;

@Service("roleService")
public class RoleService {
	@Resource
	private RoleDao roleDao;
	@Resource
	private MenuDao menuDao;

	public void save(Role role, String menuIds) {
		if (null == role.getId() || "".equals(role.getId())) {
			role.setId(null);
		}
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
		if (null != role.getId()) {
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
	public List<Role> list(Role role, int start, int limit) {
		StringBuilder hql = new StringBuilder("from Role i where 1 = 1 ");
		if (!"".equals(role.getKey()) && null != role.getKey()) {
			hql.append("and roleName like '%"+role.getKey()+"%'");
		}
		return roleDao.findPaginationListByHQL(hql.toString(), start, limit);
	}
	public Role findRoleById(String id) {
		return roleDao.executeByHQL("from Role").get(0);
	}
	public static void main(String[] args) {
		ClassPathXmlApplicationContext cxp = new ClassPathXmlApplicationContext("applicationContext.xml");
		//RoleService roleService = (RoleService)cxp.getBean("roleService");
		//System.out.println(roleService.list(new Role(), 0, 10).size());
		RoleDao roleDao = (RoleDao)cxp.getBean("roleDao");
		List<Role> role = roleDao.findListByHQL("from Role");
		System.out.println(role.size());
		try {
			Thread.sleep(2000);
		} catch (Exception e) {
			// TODO: handle exception
		}
		List<Role> role2 = roleDao.findListByHQL("from Role");
		System.out.println(role2.size());
	}
	/**
	 * 统计总数
	 * @return
	 */
	public int count() {
		return roleDao.getCountByHql("select count(i) from Role i");
	}
	public void remove(Role role) {
		roleDao.delete(role);
	}
}
