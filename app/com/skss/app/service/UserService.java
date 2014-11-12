package com.skss.app.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.skss.app.dao.UserDao;
import com.skss.app.entity.User;

@Repository
public class UserService {
	@Resource
	private UserDao userDao;
	
	public void saveUser(User user) {
		this.userDao.saveUser(user);
	}
	
	public List<User> pagelist(int start, int limit) {
		return this.userDao.pagelist(start, limit);
	}
}
