package com.greenfoarfece.foodie.main;

import java.util.Date;

import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.greenfoarfece.foodie.main.config.BaseConfiguration;
import com.greenfoarfece.foodie.main.entity.User;
import com.greenfoarfece.foodie.main.enumeration.Role;
import com.greenfoarfece.foodie.main.service.UserService;

public class App {
	
	public static void main(String[] args) {
		ApplicationContext ctx = new AnnotationConfigApplicationContext(BaseConfiguration.class);
		UserService svc = (UserService) ctx.getBean("userService");
		Date current = new Date();
		User user = User.builder()
						.createdBy("Sidki")
						.createdOn(current)
						.lastUpdatedBy("Sidki")
						.lastUpdatedOn(current)
						.password("aP455w0rd")
						.role(Role.ADMIN)
						.userName("Sidki")
						.build();
		svc.insert(user);
		System.out.println(svc.getUser((long) 1).toString());
		User userUpdate = svc.getUser((long) 1);
		userUpdate.setLastUpdatedBy("Sayid Updated");
		userUpdate.setLastUpdatedOn(new Date());
		svc.update(userUpdate);
		System.out.println(svc.getUser((long) 1).toString());
		svc.delete((long) 1);
		System.out.println(svc.getUserIncludeDeleted((long) 1).getStatus());
		svc.deletePermanently(svc.getUserIncludeDeleted((long) 1));
		System.out.println(svc.getUserIncludeDeleted((long) 1));
		((ConfigurableApplicationContext) ctx).close();
	}
	
}