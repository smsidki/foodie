package com.greenfoarfece.foodie.main.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.greenfoarfece.foodie.main.dao.UserDao;
import com.greenfoarfece.foodie.main.entity.User;
import com.greenfoarfece.foodie.main.service.UserService;

@Repository("userService")
public class UserServiceImpl implements UserService {
	
	@Autowired
	@Qualifier("userDao")
	UserDao userRepo;

	@Override
	public User getUser() {
		return userRepo.find(1);
	}
	
}
