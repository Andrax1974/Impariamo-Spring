package com.xantrix.webapp.config.security;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
 
public class SpringSecurityUserContext 
{
	
	SecurityContext context = SecurityContextHolder.getContext();
	Authentication authentication = context.getAuthentication();
	
	public String getCurrentClient()
	{
		if (authentication == null)
		{
			return null;
		}
				
		return  authentication.getName();
	}
	
}
