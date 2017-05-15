package com.greenfoarfece.foodie.main.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.greenfoarfece.foodie.main.dao.UserDao;
import com.greenfoarfece.foodie.main.entity.User;
import com.greenfoarfece.foodie.main.service.UserService;

@Service("userService")
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDao userDao;

	@Override
	public User insert(User user) {
		return userDao.save(user);
	}

	@Override
	public User update(User user) {
		User result = null;
		if (user.getId() != null && userDao.isExist(user.getId())) {
			result = userDao.save(user);
		}
		return result;
	}

	@Override
	public User getUser(Long key) {
		return userDao.find(key);
	}

	@Override
	public User getUserIncludeDeleted(Long pk) {
		return userDao.findIncludeDeleted(pk);
	}

	@Override
	public boolean delete(Long pk) {
		return userDao.delete(pk);
	}

	@Override
	public boolean deletePermanently(User user) {
		return userDao.deletePermanently(user);
	}
	
}
