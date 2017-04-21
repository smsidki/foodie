package com.greenfoarfece.foodie.main;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.greenfoarfece.foodie.main.config.BaseConfiguration;
import com.greenfoarfece.foodie.main.entity.User;
import com.greenfoarfece.foodie.main.service.UserService;

public class App {

	private static ApplicationContext ctx;

	public static void main(String[] args) {
		
		ctx = new AnnotationConfigApplicationContext(BaseConfiguration.class);
		UserService svc = (UserService) ctx.getBean("userService");
		User user = svc.getUser();
		
		System.out.println(user.getId());
		System.out.println(user.getUserName());
		System.out.println(user.getPassword());
		
	}
	
}