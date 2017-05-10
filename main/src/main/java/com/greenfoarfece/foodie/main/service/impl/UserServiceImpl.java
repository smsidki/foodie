package com.greenfoarfece.foodie.main.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.greenfoarfece.foodie.main.dao.UserDao;
import com.greenfoarfece.foodie.main.entity.User;
import com.greenfoarfece.foodie.main.service.UserService;

@Service("userService")
public class UserServiceImpl implements UserService {

	@Autowired
	UserDao userDao;

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

	/*
	@Override
	public User getUser() {
		return userDao.find(Long.valueOf(1));
	}
	
	@Override
	public User getUserIncludeDeleted() {
		return userDao.findIncludeDeleted(Long.valueOf(1));
	}

	@Override
	public User insert() {
		return userDao.save(generateUser());
	}

	@Override
	public boolean delete() {
		return userDao.delete(Long.valueOf(1));
	}
	
	@Override
	public boolean deletePermanently() {
		return userDao.deletePermanently(getUserIncludeDeleted());
	}

	private User generateUser() {
		Date date = new Date();
		User userAll = new User(Long.valueOf(0), "Sidqi", "Sidqi", date, date, Status.ACTIVE, "koko", "ayayawae", Role.CUSTOMER);
		User userNo = new User();
		userNo.setCreatedBy("Sidqi");
		userNo.setCreatedOn(date);
		userNo.setLastUpdatedBy("Sidqi");
		userNo.setLastUpdatedOn(date);
		userNo.setPassword("ayoayo");
		userNo.setRole(Role.ADMIN);
		userNo.setStatus(Status.ACTIVE);
		userNo.setUserName("Ohaiyo");
		return User.builder()
				   .createdBy("Sidqi")
				   .createdOn(date)
				   .lastUpdatedBy("Sidqi")
				   .lastUpdatedOn(date)
				   .password("Ohiya")
				   .role(Role.MERCHANT)
				   .status(Status.ACTIVE)
				   .userName("Dahen")
				   .build();
	}
	*/
	
}
