package com.greenfoarfece.foodie.main.dao.impl;

import org.springframework.stereotype.Repository;

import com.greenfoarfece.foodie.main.dao.UserDao;
import com.greenfoarfece.foodie.main.entity.User;

@Repository("userDao")
public class UserDaoImpl implements UserDao {

	@Override
	public User find(int PK) {
		User user = new User();
		user.setId(PK);
		user.setUserName("Sayid Sidqi");
		user.setPassword("smsidki");
		return user;
	}

}
