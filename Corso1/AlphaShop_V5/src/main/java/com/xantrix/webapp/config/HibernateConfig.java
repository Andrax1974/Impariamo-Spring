package com.xantrix.webapp.config;

import java.util.Properties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.xantrix.webapp.domain.Clienti;
import com.xantrix.webapp.domain.Utenti;

import org.springframework.core.env.Environment;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;

import static org.hibernate.cfg.Environment.*;

//https://docs.jboss.org/hibernate/orm/5.0/manual/en-US/html/ch03.html

@Configuration
@EnableTransactionManagement
@PropertySource("classpath:application.properties")
@ComponentScan(
{ "com.xantrix.webapp" })
public class HibernateConfig
{
	@Autowired
	private Environment env;

	@Bean
	public LocalSessionFactoryBean getSessionFactory()
	{
		LocalSessionFactoryBean factoryBean = new LocalSessionFactoryBean();

		Properties props = new Properties();

		// Setting JDBC properties
		props.put(DRIVER, env.getProperty("jdbc.driverClassName"));
		props.put(URL, env.getProperty("jdbc.url"));
		props.put(USER, env.getProperty("jdbc.username"));
		props.put(PASS, env.getProperty("jdbc.password"));

		// Setting Hibernate properties
		props.put(SHOW_SQL, env.getProperty("hibernate.show_sql"));
		props.put(DIALECT, env.getProperty("hibernate.dialect"));
		props.put(HBM2DDL_AUTO, env.getProperty("hibernate.hbm2ddl.auto"));

		// Setting C3P0 properties
		props.put(C3P0_MIN_SIZE, env.getProperty("hibernate.c3p0.min_size"));
		props.put(C3P0_MAX_SIZE, env.getProperty("hibernate.c3p0.max_size"));
		props.put(C3P0_ACQUIRE_INCREMENT, env.getProperty("hibernate.c3p0.acquire_increment"));
		props.put(C3P0_TIMEOUT, env.getProperty("hibernate.c3p0.timeout"));
		props.put(C3P0_MAX_STATEMENTS, env.getProperty("hibernate.c3p0.max_statements"));

		factoryBean.setHibernateProperties(props);
		
		factoryBean.setAnnotatedClasses(Utenti.class, Clienti.class);

		return factoryBean;
	}
}
