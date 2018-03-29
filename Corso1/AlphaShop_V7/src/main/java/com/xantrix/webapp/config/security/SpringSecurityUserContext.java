package com.xantrix.webapp.config.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import com.xantrix.webapp.entities.Clienti;
import com.xantrix.webapp.service.ClientiService;


//@Component
public class SpringSecurityUserContext 
{
	@Autowired
	private ClientiService clientiService;
	
	SecurityContext context = SecurityContextHolder.getContext();
	Authentication authentication = context.getAuthentication();
	
	public Clienti getCurrentClient()
	{
		Clienti retVal = null;
		
		if (authentication == null)
		{
			return null;
		}
		
		String UserId = authentication.getName();
		
		retVal =  clientiService.SelByUserId(UserId);
				
		return retVal;
	}
	
}
