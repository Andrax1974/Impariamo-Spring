package com.xantrix.webapp.config.security;

import javax.servlet.http.HttpServletRequest;

import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.rememberme.PersistentTokenBasedRememberMeServices;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;

public class CustomPersistentTokenBasedRememberMeServices extends PersistentTokenBasedRememberMeServices 
{

	public CustomPersistentTokenBasedRememberMeServices(String key, 
			UserDetailsService userDetailsService, PersistentTokenRepository tokenRepository)
	{
		super(key, userDetailsService, tokenRepository);
	}
	
	@Override
	protected boolean rememberMeRequested(HttpServletRequest request, String parameter) 
	{
		String isRegularLogin = request.getParameter("isRegularLogin");
		
		if (isRegularLogin != null && "true".equals(isRegularLogin)) 
		{
			return super.rememberMeRequested(request, parameter);
		}
		else
		{
			return true;
		}
	}

}
