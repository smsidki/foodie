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
	public User getUser() {
		return userDao.find(1);
	}
	
}
