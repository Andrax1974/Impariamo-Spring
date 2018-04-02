package com.xantrix.webapp.config.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.security.web.authentication.logout.SimpleUrlLogoutSuccessHandler;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
import org.springframework.stereotype.Component;

@Component
public class CustomLogoutSuccessHandler implements LogoutSuccessHandler
{
	private static final Logger logger = LogManager.getLogger("Logout");
	
	@Autowired
	@Qualifier("persistentTokenRepository")
    private PersistentTokenRepository persistentTokenRepository;
	
	/*
	public CustomLogoutSuccessHandler()
	{
		super();
	}
	*/

	@Override
	public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, 
			Authentication authentication)
			throws IOException, ServletException
	{
		if (authentication != null)
		{
			 logger.info("utente: " + authentication.getName()); 
			 String UserId = authentication.getName();	 
			 persistentTokenRepository.removeUserTokens(UserId);
			 
			
			 String refererUrl = request.getHeader("Referer");
			 logger.info("Logout from: " + refererUrl); 
		       
		}
		
		// perform other required operation
		String URL = request.getContextPath() + "/app";
		
		response.setStatus(HttpStatus.OK.value());
		response.sendRedirect(URL);
		//response.getWriter().flush();
	}
}
