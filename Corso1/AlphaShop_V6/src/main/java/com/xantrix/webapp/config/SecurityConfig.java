package com.xantrix.webapp.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter
{
	@Override
	public void configure(final AuthenticationManagerBuilder auth) throws Exception
	{
		auth
		.inMemoryAuthentication()
		.withUser("Nicola").password("123Stella")
		.roles("USER");
	}

	@Override
	protected void configure(final HttpSecurity http) throws Exception
	{
		http
		.authorizeRequests()
		.antMatchers("/login/**").permitAll()
		.antMatchers("/**")
		.access("hasRole('USER')")
		.and()
			.formLogin()
				.loginPage("/login/form")
				//.loginProcessingUrl("/login")
				.failureUrl("/login/form?error")
				.usernameParameter("userId")
                .passwordParameter("password")
		.and()
			.httpBasic()
		.and()
			.logout()
		.and()
			.csrf().disable();
	}
}
