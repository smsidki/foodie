package com.greenfoarfece.foodie.main.service;

import com.greenfoarfece.foodie.main.entity.User;

public interface UserService {
	
	public User insert(User user);
	public User update(User user);
	public User getUser(Long pk);
	public User getUserIncludeDeleted(Long pk);
	public boolean delete(Long pk);
	public boolean deletePermanently(User user);

}
