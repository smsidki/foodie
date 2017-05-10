package com.greenfoarfece.foodie.main.dao.impl;

import org.springframework.stereotype.Repository;

import com.greenfoarfece.foodie.common.dao.impl.CommonDaoImpl;
import com.greenfoarfece.foodie.main.dao.UserDao;
import com.greenfoarfece.foodie.main.entity.User;

/**
 * Implementation of {@link UserDao}.
 * 
 * @since 10 Mei 2017
 * @version 0.0.1-SNAPSHOT
 * @author sayid.sidqi
 */
@Repository("userDao")
public class UserDaoImpl extends CommonDaoImpl<User, Long> implements UserDao {
	
	@SuppressWarnings("rawtypes")
	@Override
	protected Class getEntityClass() {
		return User.class;
	}
		
}
