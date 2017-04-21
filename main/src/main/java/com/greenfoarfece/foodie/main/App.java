package com.greenfoarfece.foodie.main;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.greenfoarfece.foodie.main.config.BaseConfiguration;
import com.greenfoarfece.foodie.main.dao.UserDao;
import com.greenfoarfece.foodie.main.entity.User;
import com.greenfoarfece.foodie.main.service.UserService;

public class App {

	private static ApplicationContext ctx;

	public static void main(String[] args) {
		
		ctx = new AnnotationConfigApplicationContext(BaseConfiguration.class);
		UserService svc = (UserService) ctx.getBean("userService");
		UserDao dao = (UserDao) ctx.getBean("userDao");
		User user = svc.getUser();
		User user2 = dao.find(5);
		
		System.out.println(user.getId());
		System.out.println(user.getUserName());
		System.out.println(user.getPassword());
		
		System.out.println(user2.getId());
		System.out.println(user2.getUserName());
		System.out.println(user2.getPassword());
		
	}
	
}