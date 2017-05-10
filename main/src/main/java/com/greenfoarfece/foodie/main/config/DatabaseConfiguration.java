package com.greenfoarfece.foodie.main.config;

import java.util.Properties;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate4.LocalSessionFactoryBean;

@Configuration
@PropertySource({
	"classpath:hibernate.properties",
	"classpath:jdbc.properties"
})
public class DatabaseConfiguration {
	
	@Autowired
	private Environment env;

	@Bean(name = "mainSessionFactory")
	public LocalSessionFactoryBean sessionFactory() {
		LocalSessionFactoryBean sf = new LocalSessionFactoryBean();
		sf.setDataSource(dataSource());
		sf.setPackagesToScan(new String[] { "com.greenfoarfece.foodie.main.entity" });
		sf.setHibernateProperties(hibernateProperties());
		return sf;
	}
	
	@Bean
	public DataSource dataSource() {
		DriverManagerDataSource ds = new DriverManagerDataSource();
		ds.setDriverClassName(env.getRequiredProperty("jdbc.driver"));
		ds.setUrl(env.getRequiredProperty("jdbc.url"));
		ds.setUsername(env.getRequiredProperty("jdbc.username"));
		ds.setPassword(env.getRequiredProperty("jdbc.password"));
		return ds;
	}

	private Properties hibernateProperties() {
		Properties prop = new Properties();
		prop.put("hibernate.dialect", env.getRequiredProperty("hibernate.dialect"));
		prop.put("hibernate.show_sql", env.getRequiredProperty("hibernate.show_sql"));
		prop.put("hibernate.format_sql", env.getRequiredProperty("hibernate.format_sql"));
		prop.put("hibernate.current_session_context_class", env.getRequiredProperty("hibernate.current_session_context_class"));
		prop.put("hibernate.hbm2ddl.auto", env.getRequiredProperty("hibernate.hbm2ddl.auto"));
		return prop;
	}
	
}
