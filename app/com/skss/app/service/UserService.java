package com.skss.app.service;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.skss.app.dao.RoleDao;
import com.skss.app.dao.UserDao;
import com.skss.app.entity.Role;
import com.skss.app.entity.User;

@Service("userService")
public class UserService {
	@Resource
	private UserDao userDao;
	@Resource
	private RoleDao roleDao;
	public void saveUser(User user, String roleId) {
		Role role = roleDao.get(roleId);
		List<Role> roles = new ArrayList<Role>();
		roles.add(role);
		user.setRoles(roles);
		this.userDao.saveUser(user);
	}
	
	public List<User> pagelist(int start, int limit) {
		return this.userDao.pagelist(start, limit);
	}
	
	public User login(User user) {
		return this.userDao.login(user);
	}
}
