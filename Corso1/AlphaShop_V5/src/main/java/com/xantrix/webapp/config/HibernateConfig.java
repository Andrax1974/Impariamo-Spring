package com.xantrix.webapp.config;

import java.util.Properties;

import javax.persistence.EntityManagerFactory;
import javax.persistence.SharedCacheMode;
import javax.persistence.ValidationMode;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.xantrix.webapp.entities.Clienti;
import com.xantrix.webapp.entities.Utenti;

import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;

import static org.hibernate.cfg.Environment.*;

@Configuration
@EnableTransactionManagement
@ComponentScan({ "com.xantrix.webapp.config" })
@PropertySource("classpath:application.properties")
public class HibernateConfig
{
	@Autowired
	private Environment env;

	@Autowired
	private DataSource dataSource;

	@Bean
	LocalContainerEntityManagerFactoryBean entityManagerFactory()
	{
		LocalContainerEntityManagerFactoryBean entityManagerFactoryBean = new LocalContainerEntityManagerFactoryBean();
		
		entityManagerFactoryBean.setDataSource(dataSource);
		entityManagerFactoryBean.setJpaVendorAdapter(this.jpaVendorAdapter());
		entityManagerFactoryBean.setPackagesToScan("com.xantrix.webapp.entities");
		entityManagerFactoryBean.setSharedCacheMode(SharedCacheMode.ENABLE_SELECTIVE);
		entityManagerFactoryBean.setValidationMode(ValidationMode.NONE);
		entityManagerFactoryBean.setJpaProperties(this.hibernateProperties());

		return entityManagerFactoryBean;
	}
	
	@Bean
	public JpaVendorAdapter jpaVendorAdapter()
	{
		HibernateJpaVendorAdapter hibernateJpaVendorAdapter = new HibernateJpaVendorAdapter();
		hibernateJpaVendorAdapter.setShowSql(true);
		hibernateJpaVendorAdapter.setGenerateDdl(true);
		hibernateJpaVendorAdapter.setDatabasePlatform(env.getRequiredProperty("hibernate.dialect"));

		return hibernateJpaVendorAdapter;
	}
	
	//https://docs.jboss.org/hibernate/orm/5.0/manual/en-US/html/ch03.html
	private Properties hibernateProperties()
	{
		Properties jpaProperties = new Properties();

		jpaProperties.put(DIALECT, env.getRequiredProperty("hibernate.dialect"));
		jpaProperties.put(HBM2DDL_AUTO, env.getRequiredProperty("hibernate.hbm2ddl.auto"));
		jpaProperties.put(SHOW_SQL, env.getRequiredProperty("hibernate.show_sql"));
		jpaProperties.put("hibernate.format_sql", env.getRequiredProperty("hibernate.format_sql"));

		// Setting C3P0 properties
		jpaProperties.put(C3P0_MIN_SIZE, env.getProperty("hibernate.c3p0.min_size"));
		jpaProperties.put(C3P0_MAX_SIZE, env.getProperty("hibernate.c3p0.max_size"));
		jpaProperties.put(C3P0_ACQUIRE_INCREMENT, env.getProperty("hibernate.c3p0.acquire_increment"));
		jpaProperties.put(C3P0_TIMEOUT, env.getProperty("hibernate.c3p0.timeout"));
		jpaProperties.put(C3P0_MAX_STATEMENTS, env.getProperty("hibernate.c3p0.max_statements"));
		
		return jpaProperties;
	}

	@Bean
	JpaTransactionManager transactionManager(EntityManagerFactory entityManagerFactory)
	{
		JpaTransactionManager transactionManager = new JpaTransactionManager();
		transactionManager.setEntityManagerFactory(entityManagerFactory);
		
		return transactionManager;
	}
}


